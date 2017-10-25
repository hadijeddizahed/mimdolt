/**
 * Created by Ghost on 11/30/2016.
 */
angular.module('myApp').factory('myService', function () {
    var formData = {
        productId:""
    };

    return {
        getData: function () {
            //You could also return specific attribute of the form data instead
            //of the entire data
            return formData;
        },
        setData: function (newFormData) {
            //You could also set specific attribute of the form data instead
            formData = newFormData
        },
        resetData: function () {
            //To be called when the data stored needs to be discarded
            formData = {};
        }
    };
});
