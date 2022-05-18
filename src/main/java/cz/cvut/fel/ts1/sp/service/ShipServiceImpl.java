package cz.cvut.fel.ts1.sp.service;

import cz.cvut.fel.ts1.sp.controller.ShipOrder;
import cz.cvut.fel.ts1.sp.model.Ship;
import cz.cvut.fel.ts1.sp.model.ShipType;
import cz.cvut.fel.ts1.sp.repository.ShipRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
@Transactional
public class ShipServiceImpl implements ShipService {

    private ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        super();
        this.shipRepository = shipRepository;
    }

    @Override
    public Ship saveShip(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public Ship getShip(Long id) {
        return shipRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteShip(Ship ship) {
        shipRepository.deleteById(ship.getId());
    }

    @Override
    public List<Ship> getShips(
            String name,
            String planet,
            ShipType shipType,
            Long after,
            Long before,
            Boolean isUsed,
            Double minSpeed,
            Double maxSpeed,
            Integer minCrewSize,
            Integer maxCrewSize,
            Double minRating,
            Double maxRating,
            Double minPrice,
            Double maxPrice
    ) {
        final Date afterDate = after == null ? null : new Date(after);
        final Date beforeDate = before == null ? null : new Date(before);
        final List<Ship> list = new ArrayList<>();
        shipRepository.findAll().forEach((ship) -> {
            if (name != null && !ship.getName().contains(name)) return;
            if (planet != null && !ship.getPlanet().contains(planet)) return;
            if (shipType != null && ship.getShipType() != shipType) return;
            if (afterDate != null && ship.getProdDate().before(afterDate)) return;
            if (beforeDate != null && ship.getProdDate().after(beforeDate)) return;
            if (isUsed != null && ship.getIsUsed().booleanValue() != isUsed.booleanValue()) return;
            if (minSpeed != null && ship.getSpeed().compareTo(minSpeed) < 0) return;
            if (maxSpeed != null && ship.getSpeed().compareTo(maxSpeed) > 0) return;
            if (minCrewSize != null && ship.getCrewSize().compareTo(minCrewSize) < 0) return;
            if (maxCrewSize != null && ship.getCrewSize().compareTo(maxCrewSize) > 0) return;
            if (minRating != null && ship.getRating().compareTo(minRating) < 0) return;
            if (maxRating != null && ship.getRating().compareTo(maxRating) > 0) return;
            if (minPrice != null && ship.getPrice().compareTo(minPrice) < 0) return;
            if (maxPrice != null && ship.getPrice().compareTo(maxPrice) > 0) return;

            list.add(ship);
        });
        return list;
    }

    @Override
    public List<Ship> sortShips(List<Ship> ships, ShipOrder order) {
        if (order != null) {
            ships.sort((ship1, ship2) -> {
                switch (order) {
                    case ID: return ship1.getId().compareTo(ship2.getId());
                    case SPEED: return ship1.getSpeed().compareTo(ship2.getSpeed());
                    case DATE: return ship1.getProdDate().compareTo(ship2.getProdDate());
                    case RATING: return ship1.getRating().compareTo(ship2.getRating());
                    default: return 0;
                }
            });
        }
        return ships;
    }

    @Override
    public List<Ship> getPage(List<Ship> ships, Integer pageNumber, Integer pageSize) {
        final int page = pageNumber == null ? 0 : pageNumber;
        final int size = pageSize == null ? 3 : pageSize;
        final int from = page * size;
        int to = from + size;
        if (to > ships.size()) to = ships.size();
        return ships.subList(from, to);
    }

    @Override
    public boolean isShipValid(Ship ship) {
        return ship != null && isStringValid(ship.getName())
                && isStringValid(ship.getPlanet())
                && isProdDateValid(ship.getProdDate())
                && isSpeedValid(ship.getSpeed())
                && isCrewSizeValid(ship.getCrewSize())
                && ship.getPrice() > 0;
    }

    @Override
    public double computeRating(double speed, boolean isUsed, Date prod) {
        final int now = 3019;
        final int prodYear = getYearFromDate(prod);
        final double k = isUsed ? 0.5 : 1;
        final double rating = 80 * speed * k / (now - prodYear + 1);
        return round(rating);
    }

    @Override
    public Ship updateShip(Ship oldShip, Ship newShip) throws IllegalArgumentException {
        boolean shouldChangeRating = false;

        final String name = newShip.getName();
        if (name != null) {
            if (isStringValid(name)) {
                oldShip.setName(name);
            } else {
                throw new IllegalArgumentException();
            }
        }

        final String planet = newShip.getPlanet();
        if (planet != null) {
            if (isStringValid(planet)) {
                oldShip.setPlanet(planet);
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (newShip.getShipType() != null) {
            oldShip.setShipType(newShip.getShipType());
        }

        final Date prodDate = newShip.getProdDate();
        if (prodDate != null) {
            if (isProdDateValid(prodDate)) {
                oldShip.setProdDate(prodDate);
                shouldChangeRating = true;
            } else {
                throw new IllegalArgumentException();
            }
        }

        final Double price = newShip.getPrice();
        if (price != null) {
            if (price > 0) {
                oldShip.setPrice(price);
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (newShip.getIsUsed() != null) {
            oldShip.setIsUsed(newShip.getIsUsed());
            shouldChangeRating = true;
        }

        final Double speed = newShip.getSpeed();
        if (speed != null) {
            if (isSpeedValid(speed)) {
                oldShip.setSpeed(speed);
                shouldChangeRating = true;
            } else {
                throw new IllegalArgumentException();
            }
        }

        final Integer crewSize = newShip.getCrewSize();
        if (crewSize != null) {
            if (isCrewSizeValid(crewSize)) {
                oldShip.setCrewSize(crewSize);
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (shouldChangeRating) {
            final double rating = computeRating(oldShip.getSpeed(), oldShip.getIsUsed(), oldShip.getProdDate());
            oldShip.setRating(rating);
        }

        shipRepository.save(oldShip);
        return oldShip;
    }

    private boolean isCrewSizeValid(Integer crewSize) {
        final int minCrewSize = 1;
        final int maxCrewSize = 9999;
        return crewSize != null && crewSize.compareTo(minCrewSize) >= 0 && crewSize.compareTo(maxCrewSize) <= 0;
    }

    private boolean isSpeedValid(Double speed) {
        final double minSpeed = 0.01;
        final double maxSpeed = 0.99;
        return speed != null && speed.compareTo(minSpeed) >= 0 && speed.compareTo(maxSpeed) <= 0;
    }

    private boolean isStringValid(String value) {
        final int maxStringLength = 50;
        return value != null && !value.isEmpty() && value.length() <= maxStringLength;
    }

    private boolean isProdDateValid(Date prodDate) {
        final Date startProd = getDateForYear(2800);
        final Date endProd = getDateForYear(3019);
        return prodDate != null && prodDate.after(startProd) && prodDate.before(endProd);
    }

    private Date getDateForYear(int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    private int getYearFromDate(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private double round(double value) {
        return Math.round(value * 100) / 100D;
    }

    public Long convertIdToLong(String pathId) {
        if (pathId == null) {
            return null;
        } else try {
            return Long.parseLong(pathId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}