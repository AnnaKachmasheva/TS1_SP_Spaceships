function loadContent(root, suffix, currentPage) {

    let objects = JSON.parse(Get(root + "/rest/ships" + suffix).responseText);
    let shipsCount = Get(root + "/rest/ships/count" + suffix).responseText;
    document.getElementById("count").innerText = "Ships found: " + shipsCount;
    let table = document.getElementById("mainTable");
    table.innerHTML = "";
    createPaging(document.getElementById("limit").value, shipsCount, currentPage);

    for (let i = 0; i < objects.length; i++) {

        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        th.appendChild(document.createTextNode(objects[i].id));
        tr.appendChild(th);

        let td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(objects[i].name));
        tr.appendChild(td1);

        let td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(objects[i].planet));
        tr.appendChild(td2);

        let td3 = document.createElement("td");
        let shipType = objects[i].shipType.charAt(0).toUpperCase() + objects[i].shipType.slice(1).toLowerCase();
        td3.appendChild(document.createTextNode(shipType));
        tr.appendChild(td3);

        let td4 = document.createElement("td");
        let date = new Date();
        date.setTime(objects[i].prodDate);
        td4.appendChild(document.createTextNode(date.getFullYear().toString()));
        tr.appendChild(td4);

        let preOwned;
        if (objects[i].isUsed) {
            preOwned = "pre-owned";
        } else {
            preOwned = "new";
        }
        let td5 = document.createElement("td");
        td5.appendChild(document.createTextNode(preOwned));
        tr.appendChild(td5);

        let td6 = document.createElement("td");
        td6.appendChild(document.createTextNode(objects[i].speed));
        tr.appendChild(td6);

        let td7 = document.createElement("td");
        td7.appendChild(document.createTextNode(objects[i].crewSize));
        tr.appendChild(td7);

        let td8 = document.createElement("td");
        td8.appendChild(document.createTextNode(objects[i].price));
        tr.appendChild(td8);

        let td9 = document.createElement("td");
        td9.appendChild(document.createTextNode(objects[i].rating));
        tr.appendChild(td9);


        let warButton = document.createElement("button");
        warButton.setAttribute("type", "button");
        warButton.setAttribute("class", "btn btn-warning btn-sm");
        warButton.appendChild(document.createTextNode("Edit"));
        warButton.addEventListener("click", function () {
            editButtonClick(root, tr, objects[i].id)
        });

        let dangerButton = document.createElement("button");
        dangerButton.setAttribute("type", "button");
        dangerButton.setAttribute("class", "btn btn-danger btn-sm");
        dangerButton.appendChild(document.createTextNode("Delete"));
        dangerButton.addEventListener("click", function () {
            processDelete(root, objects[i].id)
        });

        let createOrderButton = document.createElement("button");
        createOrderButton.setAttribute("type", "button");
        createOrderButton.setAttribute("class", "btn btn-info btn-sm");
        createOrderButton.appendChild(document.createTextNode("New order"));
        createOrderButton.addEventListener("click", function () {
            newOrderButtonClick(root, tr, objects[i].id)
        });

        let td10 = document.createElement("td");
        td10.appendChild(warButton);
        tr.appendChild(td10);

        let td11 = document.createElement("td");
        td11.appendChild(dangerButton);
        tr.appendChild(td11);
        table.appendChild(tr);

        let td12 = document.createElement("td");
        td11.appendChild(createOrderButton);
        tr.appendChild(td12);

        table.appendChild(tr);
    }

    let tableOrders = document.getElementById("tableOrders");
    tableOrders.innerHTML = "";
    let ordersObjects = JSON.parse(Get(root + "/rest/orders" + suffix).responseText);

    for (let i = 0; i < ordersObjects.length; i++) {

        let tr = document.createElement("tr");
        let th = document.createElement("th");
        th.setAttribute("scope", "row");
        th.appendChild(document.createTextNode(ordersObjects[i].id));
        tr.appendChild(th);

        let td1 = document.createElement("td");
        td1.appendChild(document.createTextNode(ordersObjects[i].ship.id));
        tr.appendChild(td1);

        let td2 = document.createElement("td");
        td2.appendChild(document.createTextNode(ordersObjects[i].state === true ? 'active' : 'archive'));
        tr.appendChild(td2);

        let td3 = document.createElement("td");
        td3.appendChild(document.createTextNode(ordersObjects[i].distance));
        tr.appendChild(td3);

        let td4 = document.createElement("td");
        td4.appendChild(document.createTextNode(ordersObjects[i].price));
        tr.appendChild(td4);

        let warButton = document.createElement("button");
        warButton.setAttribute("type", "button");
        warButton.setAttribute("class", "btn btn-warning btn-sm");
        warButton.appendChild(document.createTextNode("Edit"));
        warButton.addEventListener("click", function () {
            editButtonClickOrder(root, tr, ordersObjects[i].id)
        });

        let dangerButton = document.createElement("button");
        dangerButton.setAttribute("type", "button");
        dangerButton.setAttribute("class", "btn btn-danger btn-sm");
        dangerButton.appendChild(document.createTextNode("Delete"));
        dangerButton.addEventListener("click", function () {
            processDeleteOrder(root, ordersObjects[i].id)
        });

        let td5 = document.createElement("td");
        td5.appendChild(warButton);
        tr.appendChild(td5);

        let td6= document.createElement("td");
        td6.appendChild(dangerButton);
        tr.appendChild(td6);
        table.appendChild(tr);

        tableOrders.appendChild(tr);
    }

    window.scrollTo(500, 100);
}

function Get(requestUrl) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("GET", requestUrl, false);
    Httpreq.send(null);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to GET " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found GET " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function post(requestUrl, body) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("POST", requestUrl, false);
    Httpreq.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    Httpreq.send(body);

    console.log(body)

    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to POST " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found POST " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function Delete(requestUrl) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("DELETE", requestUrl, false);
    Httpreq.send(null);
    console.log(Httpreq.status);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function processSearch(root, currentPage) {
    let name = document.getElementById("inputName").value;

    let planet = document.getElementById("inputPlanet").value;

    let dateAfter = new Date();
    let valueAfter = +document.getElementById("inputProdYearAfter").value;
    let yearAfter = dateAfter.setFullYear(+document.getElementById("inputProdYearAfter").value);
    if (valueAfter === 0) {
        yearAfter = "";
    }
    let dateBefore = new Date();
    let valueBefore = +document.getElementById("inputProdYearBefore").value;
    let yearBefore = dateBefore.setFullYear(+document.getElementById("inputProdYearBefore").value);
    if (valueBefore === 0) {
        yearBefore = "";
    }

    let crewSizeMin = document.getElementById("inputCrewSizeMin").value;
    let crewSizeMax = document.getElementById("inputCrewSizeMax").value;

    let speedMin = document.getElementById("inputSpeedMin").value;
    let speedMax = document.getElementById("inputSpeedMax").value;

    let ratingMin = document.getElementById("inputRatingMin").value;
    let ratingMax = document.getElementById("inputRatingMax").value;

    let shipType = document.getElementById("inputShipType").value;

    let priceMax = document.getElementById("inputPriceMax").value;
    let priceMin = document.getElementById("inputPriceMin").value;

    let order = document.getElementById("order").value;
    let isUsed = null;
    let limit = document.getElementById("limit").value;
    if (document.getElementById("inlineRadio2").checked) {
        isUsed = true;
    } else if (document.getElementById("inlineRadio3").checked) {
        isUsed = false;
    }
    let sufix = "?";

    if (name !== "") {
        sufix += "name=" + name;
    }

    if (planet !== "") {
        sufix += "&planet=" + planet;
    }

    if (shipType !== "Any") {
        sufix += "&shipType=" + shipType.toUpperCase();
    }

    if (yearAfter !== "") {
        sufix += "&after=" + yearAfter;
    }

    if (yearBefore !== "") {
        sufix += "&before=" + yearBefore;
    }

    if (isUsed !== null) {
        sufix += "&isUsed=" + isUsed;
    }

    if (speedMin !== "") {
        sufix += "&minSpeed=" + speedMin;
    }

    if (speedMax !== "") {
        sufix += "&maxSpeed=" + speedMax;
    }

    if (crewSizeMin !== "") {
        sufix += "&minCrewSize=" + crewSizeMin;
    }

    if (crewSizeMax !== "") {
        sufix += "&maxCrewSize=" + crewSizeMax;
    }

    if (ratingMin !== "") {
        sufix += "&minRating=" + ratingMin;
    }

    if (ratingMax !== "") {
        sufix += "&maxRating=" + ratingMax;
    }

    if (priceMin !== "") {
        sufix += "&minPrice=" + priceMin;
    }

    if (priceMax !== "") {
        sufix += "&maxPrice=" + priceMax;
    }

    sufix += "&pageNumber=" + (+currentPage - 1);
    sufix += "&pageSize=" + +limit;
    console.log(limit);

    if (order === "Prod year") {
        order = "date";
    }

    sufix += "&order=" + order.toUpperCase();
    loadContent(root, sufix, currentPage);
}

function createPaging(shipsInPage, shipsSummary, currentPage) {
    let paggingBar = document.getElementById("pagging-bar");
    paggingBar.innerHTML = "";
    let pagesCount = shipsSummary / shipsInPage;
    if (pagesCount > 1) {

        for (let i = 0; i < pagesCount; i++) {
            let li = document.createElement("li");
            if (i === currentPage - 1) {
                li.setAttribute("class", "page-item disabled");
            } else {
                li.setAttribute("class", "page-item");
            }
            let a = document.createElement("a");
            a.setAttribute("class", "page-link");
            a.setAttribute("href", "#");
            let root = document.getElementById("root").getAttribute("about");
            a.setAttribute("onclick", "processSearch('" + root + "', " + (i + 1) + ")");
            a.appendChild(document.createTextNode(i + 1));
            li.appendChild(a);
            paggingBar.appendChild(li);
        }
    }
}

function newOrderButtonClick(root, element, id) {
    let o = JSON.parse(Get(root + "/rest/ships/" + id).responseText);
    if (document.body.contains(document.getElementById("neworder" + id))) {
        document.getElementById("neworder" + id).remove();
        return;
    }
    let tr = document.createElement("tr");
    tr.setAttribute("id", "neworder" + o.id);

    let th = document.createElement("th");
    th.setAttribute("scope", "row");
    th.appendChild(document.createTextNode(""));
    tr.appendChild(th);

    let td1 = document.createElement("td");
    td1.appendChild(document.createTextNode('Distance'));
    tr.appendChild(td1);

    let td2 = document.createElement("td");
    let distanceInput = document.createElement("input");
    distanceInput.setAttribute("type", "number");
    distanceInput.setAttribute("min", "1");
    distanceInput.setAttribute("size", "4");
    distanceInput.setAttribute("step", "1");
    distanceInput.setAttribute("style", "font-family:monospace");
    distanceInput.setAttribute("class", "form-control");
    distanceInput.setAttribute("id", "orderDistance" + o.id);
    distanceInput.setAttribute("value", o.distance);
    td2.appendChild(distanceInput)
    tr.appendChild(td2);

    let td3 = document.createElement("td");
    let saveButton = document.createElement("button");
    saveButton.setAttribute("type", "button");
    saveButton.setAttribute("class", "btn btn-success btn-sm");
    saveButton.addEventListener("click", function () {
        sendNewOrder(root, o.id, o)
    });
    saveButton.appendChild(document.createTextNode("Create"));
    td3.appendChild(saveButton);
    tr.appendChild(td3);

    element.insertAdjacentElement("afterEnd", tr);
}

function sendNewOrder(root, id, ship) {
    let body = {};
    body.ship = ship;
    body.distance = parseInt(document.getElementById("orderDistance" + id).value);
    body.state = true;
    body.price = ship.price * body.distance;

    let response = post(root + "/rest/orders", JSON.stringify(body));
    if (response.status === 200) {
        if (document.body.contains(document.getElementById("neworder" + id))) {
            document.getElementById("neworder" + id).remove();
        }
    }
    processSearch(root, 1);
}

function editButtonClickOrder(root, element, id) {
    let objectToUpdate = JSON.parse(Get(root + "/rest/orders/" + id).responseText);

    if (document.body.contains(document.getElementById("updateOrder" + id))) {
        document.getElementById("updateOrder" + id).remove();
        return;
    }
    let tr = document.createElement("tr");
    tr.setAttribute("id", "updateOrder" + objectToUpdate.id);

    let th = document.createElement("th");
    th.setAttribute("scope", "row");
    th.appendChild(document.createTextNode(""));
    tr.appendChild(th);

    let td1 = document.createElement("td");
    td1.appendChild(document.createTextNode('State'));
    tr.appendChild(td1);

    let td2 = document.createElement("td");
    let isUsedInput = document.createElement("select");
    isUsedInput.setAttribute("class", "form-control input-sm");
    isUsedInput.setAttribute("style", "font-family:monospace");
    isUsedInput.setAttribute("id", "updateIsActive" + objectToUpdate.id);
    let isUsedType = ["active", "archive"];
    for (let i = 0; i < isUsedType.length; i++) {
        let option = document.createElement("option");
        if (objectToUpdate.isUsed === true && isUsedType[i] === "active") {
            option.selected = true;
        }
        if (objectToUpdate.isUsed === false && isUsedType[i] === "archive") {
            option.selected = true;
        }
        option.appendChild(document.createTextNode(isUsedType[i]));
        isUsedInput.appendChild(option);
    }
    td2.appendChild(isUsedInput);
    tr.appendChild(td2);

    let td3 = document.createElement("td");
    td3.appendChild(document.createTextNode('Distance'));
    tr.appendChild(td3);

    let td4 = document.createElement("td");
    let distanceInput = document.createElement("input");
    distanceInput.setAttribute("type", "number");
    distanceInput.setAttribute("min", "1");
    distanceInput.setAttribute("size", "2");
    distanceInput.setAttribute("step", "1");
    distanceInput.setAttribute("style", "font-family:monospace");
    distanceInput.setAttribute("class", "form-control");
    distanceInput.setAttribute("id", "updateDistance" + objectToUpdate.id);
    distanceInput.setAttribute("value", objectToUpdate.distance);
    td4.appendChild(distanceInput);
    tr.appendChild(td4);

    let td5 = document.createElement("td");
    td5.appendChild(document.createTextNode(""));
    tr.appendChild(td5);

    let td6 = document.createElement("td");
    let saveButton = document.createElement("button");
    saveButton.setAttribute("type", "button");
    saveButton.setAttribute("class", "btn btn-success btn-sm");
    saveButton.addEventListener("click", function () {
        sendUpdateOrder(root, objectToUpdate, objectToUpdate.id)
    });
    saveButton.appendChild(document.createTextNode("Save"));
    td6.appendChild(saveButton);
    tr.appendChild(td6);

    element.insertAdjacentElement("afterEnd", tr);
}

function editButtonClick(root, element, id) {
    let objectToUpdate = JSON.parse(Get(root + "/rest/ships/" + id).responseText);
    if (document.body.contains(document.getElementById("update" + id))) {
        document.getElementById("update" + id).remove();
        return;
    }
    let tr = document.createElement("tr");
    tr.setAttribute("id", "update" + objectToUpdate.id);

    let th = document.createElement("th");
    th.setAttribute("scope", "row");
    th.appendChild(document.createTextNode(""));
    tr.appendChild(th);

    let td1 = document.createElement("td");
    let nameInput = document.createElement("input");
    nameInput.setAttribute("type", "text");
    nameInput.setAttribute("class", "form-control");
    nameInput.setAttribute("size", "10");
    nameInput.setAttribute("style", "font-family:monospace");
    nameInput.setAttribute("id", "updateName" + objectToUpdate.id);
    nameInput.setAttribute("value", objectToUpdate.name);
    td1.appendChild(nameInput);
    tr.appendChild(td1);

    let td2 = document.createElement("td");
    let planetInput = document.createElement("input");
    planetInput.setAttribute("type", "text");
    planetInput.setAttribute("class", "form-control input-sm");
    planetInput.setAttribute("size", "6");
    planetInput.setAttribute("style", "font-family:monospace");
    planetInput.setAttribute("id", "updatePlanet" + objectToUpdate.id);
    planetInput.setAttribute("value", objectToUpdate.planet);
    td2.appendChild(planetInput);
    tr.appendChild(td2);

    let td3 = document.createElement("td");
    let shipTypeInput = document.createElement("select");
    shipTypeInput.setAttribute("class", "form-control input-sm");
    shipTypeInput.setAttribute("id", "updateShipType" + objectToUpdate.id);
    shipTypeInput.setAttribute("style", "font-family:monospace");
    let shipType = ["Transport", "Military", "Merchant"];
    for (let i = 0; i < shipType.length; i++) {
        let option = document.createElement("option");
        if (shipType[i].toUpperCase() === objectToUpdate.shipType.toUpperCase()) {
            option.selected = true;
        }
        option.appendChild(document.createTextNode(shipType[i]));
        shipTypeInput.appendChild(option);
    }
    td3.appendChild(shipTypeInput);
    tr.appendChild(td3);

    let td4 = document.createElement("td");
    let date = new Date();
    date.setTime(objectToUpdate.prodDate);
    let yearInput = document.createElement("input");
    yearInput.setAttribute("type", "number");
    yearInput.setAttribute("min", "1900");
    yearInput.setAttribute("max", "3019");
    yearInput.setAttribute("size", "4");
    yearInput.setAttribute("style", "font-family:monospace");
    yearInput.setAttribute("step", "1");
    yearInput.setAttribute("class", "form-control");
    yearInput.setAttribute("id", "updateProdDate" + objectToUpdate.id);
    yearInput.setAttribute("value", "" + date.getFullYear());
    td4.appendChild(yearInput);
    tr.appendChild(td4);

    let td5 = document.createElement("td");
    let isUsedInput = document.createElement("select");
    isUsedInput.setAttribute("class", "form-control input-sm");
    isUsedInput.setAttribute("style", "font-family:monospace");
    isUsedInput.setAttribute("id", "updateIsUsed" + objectToUpdate.id);
    let isUsedType = ["new", "pre-owned"];
    for (let i = 0; i < isUsedType.length; i++) {
        let option = document.createElement("option");
        if (objectToUpdate.isUsed === true && isUsedType[i] === "pre-owned") {
            option.selected = true;
        }
        if (objectToUpdate.isUsed === false && isUsedType[i] === "new") {
            option.selected = true;
        }
        option.appendChild(document.createTextNode(isUsedType[i]));
        isUsedInput.appendChild(option);
    }
    td5.appendChild(isUsedInput);
    tr.appendChild(td5);

    let td6 = document.createElement("td");
    let speedInput = document.createElement("input");
    speedInput.setAttribute("type", "number");
    speedInput.setAttribute("min", "0");
    speedInput.setAttribute("max", "1");
    speedInput.setAttribute("size", "3");
    speedInput.setAttribute("step", "00.1");
    speedInput.setAttribute("style", "font-family:monospace");
    speedInput.setAttribute("class", "form-control");
    speedInput.setAttribute("id", "updateSpeed" + objectToUpdate.id);
    speedInput.setAttribute("value", objectToUpdate.speed);
    td6.appendChild(speedInput);
    tr.appendChild(td6);

    let td7 = document.createElement("td");
    let crewSizeInput = document.createElement("input");
    crewSizeInput.setAttribute("type", "number");
    crewSizeInput.setAttribute("min", "1");
    crewSizeInput.setAttribute("max", "9999");
    crewSizeInput.setAttribute("size", "4");
    crewSizeInput.setAttribute("style", "font-family:monospace");
    crewSizeInput.setAttribute("step", "1");
    crewSizeInput.setAttribute("class", "form-control");
    crewSizeInput.setAttribute("id", "updateCrewSize" + objectToUpdate.id);
    crewSizeInput.setAttribute("value", objectToUpdate.crewSize);
    td7.appendChild(crewSizeInput);
    tr.appendChild(td7);

    let td8 = document.createElement("td");
    let priceInput = document.createElement("input");
    priceInput.setAttribute("type", "number");
    priceInput.setAttribute("min", "0");
    priceInput.setAttribute("size", "3");
    priceInput.setAttribute("style", "font-family:monospace");
    priceInput.setAttribute("step", "1");
    priceInput.setAttribute("class", "form-control");
    priceInput.setAttribute("id", "updatePrice" + objectToUpdate.id);
    priceInput.setAttribute("value", objectToUpdate.price);
    td8.appendChild(priceInput);
    tr.appendChild(td8);

    let td9 = document.createElement("td");
    td9.appendChild(document.createTextNode(objectToUpdate.rating));
    tr.appendChild(td8);

    let td10 = document.createElement("td");
    td8.appendChild(document.createTextNode(""));
    tr.appendChild(td10);

    let td11 = document.createElement("td");
    let saveButton = document.createElement("button");
    saveButton.setAttribute("type", "button");
    saveButton.setAttribute("class", "btn btn-success btn-sm");
    saveButton.addEventListener("click", function () {
        sendUpdate(root, objectToUpdate.id)
    });
    saveButton.appendChild(document.createTextNode("Save"));
    td11.appendChild(saveButton);
    tr.appendChild(td11);

    element.insertAdjacentElement("afterEnd", tr);
}

function sendUpdateOrder(root, objectToUpdate, id) {
    let body = {};
    body = objectToUpdate;
    let isActive = document.getElementById("updateIsActive" + id).value;
    body.state = (isActive === "active");
    body.distance = document.getElementById("updateDistance" + id).value;

    post( root +  "rest/orders/" + id, JSON.stringify(body));
    loadContent(root, "", 1);
}

function sendUpdate(root, id) {
    let body = {};
    body.name = document.getElementById("updateName" + id).value;
    body.planet = document.getElementById("updatePlanet" + id).value;
    body.shipType = document.getElementById("updateShipType" + id).value.toUpperCase();
    let date = new Date();
    date.setFullYear(+document.getElementById("updateProdDate" + id).value);
    body.prodDate = date.getTime();
    let isUsed = document.getElementById("updateIsUsed" + id).value;
    body.isUsed = isUsed !== "new";
    body.speed = document.getElementById("updateSpeed" + id).value;
    body.crewSize = document.getElementById("updateCrewSize" + id).value;
    body.price = document.getElementById("updatePrice" + id).value;

    post( root +  "rest/ships/" + id, JSON.stringify(body));
    loadContent(root, "", 1);
}

function clickCreate() {
    let elem = document.getElementById("createButton");
    if (elem.style.display === "none") {
        elem.style.display = "block";
    } else {
        elem.style.display = "none";
    }
}

function processCreate(root) {
    let body = {};
    body.name = document.getElementById("inputNameNew").value;
    body.planet = document.getElementById("inputPlanetNew").value;
    body.shipType = document.getElementById("inputShipTypeNew").value.toUpperCase();
    let date = new Date();
    date.setFullYear(+document.getElementById("inputProdYearNew").value);
    body.prodDate = date.getTime();
    if (document.getElementById("inlineRadioNew1").checked) {
        body.isUsed = true;
    } else if (document.getElementById("inlineRadioNew2").checked) {
        body.isUsed = false;
    }
    body.speed = document.getElementById("inputSpeedNew").value;
    body.crewSize = document.getElementById("inputCrewSizeNew").value;
    body.price = document.getElementById("inputPriceNew").value;

    let response = post(root + "rest/ships/", JSON.stringify(body));
    if (response.status === 200) {
        document.getElementById("inputNameNew").value = "";
        document.getElementById("inputPlanetNew").value = "";
        document.getElementById("inputShipTypeNew").value = "Transport";
        document.getElementById("inputProdYearNew").value = "";
        if (document.getElementById("inlineRadioNew2").checked) {
            document.getElementById("inlineRadioNew2").checked = false;
            document.getElementById("inlineRadioNew1").checked = true;
        }
        document.getElementById("inputSpeedNew").value = "";
        document.getElementById("inputCrewSizeNew").value = "";
        document.getElementById("inputPriceNew").value = "";
    }
    processSearch(root, 1);
}

function processDelete(root, id) {
    Delete(root + "rest/ships/" + id);
    processSearch(root, 1);
}

function processDeleteOrder(root, id) {
    Delete(root + "rest/orders/" + id);
    processSearch(root, 1);
}