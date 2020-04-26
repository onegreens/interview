function parseEntityList(list) {
    var array = [];
    if (list) {
        var len = list.length;
        for (let index = 0; index < len; index++) {
            array.push(parseEntity(list[index]));
        }
    }
    return array;
}

function parseEntity(entity) {
    var obj = {};

    if (entity) {
        for (var key in entity) {
            if (entity.hasOwnProperty(key)) {
                obj[key] = entity[key];
            }
        }
        if (obj["children"]) {
            obj["children"] = parseEntityList(obj["children"]);
        } else {
            delete obj["children"];
        }
    }

    return obj;
}

export default {
    parseEntityList
}