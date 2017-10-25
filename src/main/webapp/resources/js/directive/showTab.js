/**
 * Created by Ghost on 2/16/2017.
 */
angular.module('myApp').directive('showTab',
    function () {
        return {
            link: ["scope", "element", "attrs",function (scope, element, attrs) {
                element.click(function(e) {
                    e.preventDefault();
                    $(element).tab('show');
                });
            }]
        };
    });
