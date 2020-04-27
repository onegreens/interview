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

function bubbleSort(array) {
    var len = array.length;
    for (let index = 0; index < len; index++) {
        for (let next = 0; next < len - 1 - index; next++) {
            if (array[next] > array[next + 1]) {
                var el = array[next];
                array[next] = array[next + 1];
                array[next + 1] = el;
            }

        }
    }
}

function bubbleSortObj(array, prop) {
    var len = array.length;
    for (let index = 0; index < len; index++) {
        for (let next = 0; next < len - 1 - index; next++) {
            if (array[next][prop] > array[next + 1][prop]) {
                var el = array[next];
                array[next] = array[next + 1];
                array[next + 1] = el;
            }

        }
    }
}

export default {
    parseEntityList,
    bubbleSortObj
}