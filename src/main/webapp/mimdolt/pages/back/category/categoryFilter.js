/**
 * Created by Ghost on 11/29/2016.
 */
angular.module('myApp').filter('flattenRows', function($filter) {
    return function(rows) {
        var flatten = [];
        angular.forEach(rows, function(row) {
            children = row.children;
            flatten.push(row);
            if (children) {
                angular.forEach(children, function(child) {
                    flatten.push(angular.extend(child, {child: true}));
                    
                });
            }
        });
        return flatten;
    }

    function addChild(flatten,row){

    }
});
