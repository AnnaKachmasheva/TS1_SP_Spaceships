package cz.cvut.fel.ts1.sp.controller;

import cz.cvut.fel.ts1.sp.model.Ship;
import cz.cvut.fel.ts1.sp.model.ShipType;
import cz.cvut.fel.ts1.sp.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShipController {

    private ShipService shipService;

    public ShipController() {
    }

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping(path = "/rest/ships")
    public List<Ship> getAllShips(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "planet", required = false) String planet,
            @RequestParam(value = "shipType", required = false) ShipType shipType,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "order", required = false) ShipOrder order,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        final List<Ship> ships = shipService.getShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, minPrice, maxPrice);

        final List<Ship> sortedShips = shipService.sortShips(ships, order);

        return shipService.getPage(sortedShips, pageNumber, pageSize);
    }

    @GetMapping(path = "/rest/ships/count")
    public Integer getShipsCount(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "planet", required = false) String planet,
            @RequestParam(value = "shipType", required = false) ShipType shipType,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice
    ) {
        return shipService.getShips(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, minPrice, maxPrice).size();
    }

    @PostMapping(path = "/rest/ships")
    @ResponseBody
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        if (!shipService.isShipValid(ship))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (ship.getIsUsed() == null)
            ship.setIsUsed(false);
        ship.setSpeed(ship.getSpeed());
        final double rating = shipService.computeRating(ship.getSpeed(), ship.getIsUsed(), ship.getProdDate());
        ship.setRating(rating);

        final Ship savedShip = shipService.saveShip(ship);

        return new ResponseEntity<>(savedShip, HttpStatus.OK);
    }

    @GetMapping(path = "/rest/ships/{id}")
    public ResponseEntity<Ship> getShip(@PathVariable(value = "id") String pathId) {
        final Long id = shipService.convertIdToLong(pathId);
        if (id == null || id <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        final Ship ship = shipService.getShip(id);
        if (ship == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @PostMapping(path = "/rest/ships/{id}")
    @ResponseBody
    public ResponseEntity<Ship> updateShip(@PathVariable(value = "id") String pathId, @RequestBody Ship ship) {
        final ResponseEntity<Ship> entity = getShip(pathId);
        final Ship savedShip = entity.getBody();
        if (savedShip == null)
            return entity;

        final Ship result;
        try {
            result = shipService.updateShip(savedShip, ship);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/rest/ships/{id}")
    public ResponseEntity<Ship> deleteShip(@PathVariable(value = "id") String pathId) {
        final ResponseEntity<Ship> entity = getShip(pathId);
        final Ship savedShip = entity.getBody();
        if (savedShip == null)
            return entity;

        shipService.deleteShip(savedShip);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}