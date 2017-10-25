/**
 * Created by Ghost on 2/19/2017.
 */

angular.module('myApp').factory('templates', function () {
    return {
        select: 'select',
        checkbox: 'checkbox',
        radio: 'radio'
    };
});

angular.module('myApp').directive('attributeTem', function (templates) {
    return {
        restrict: 'E',
        templateUrl: function ($elem, $attr) {
            var r = templates[$attr.mode];
            alert($attr.mode);
            return templates[$attr.mode];
        },
        scope: {
            values: '=values',
            attribute: '='
        }
    };
});

angular.module('myApp').directive('attributeInput', function () {
    return {
        restrict: "AE",
        scope: {attribute: "="},
        link: function (scope) {
            var templateToUse = '../Templates/default.html';
            if (scope.attribute.type == 'select') {
                templateToUse = 'select';
            } else if (scope.attribute.type == 'checkbox') {
                templateToUse = 'checkbox';
            } // etc.
            scope.myTemplate = templateToUse;
        },
        template: "<div ng-include='myTemplate'></div>"
    }
});
