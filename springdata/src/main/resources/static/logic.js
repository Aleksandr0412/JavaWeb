var app = angular.module('app', ['ngRoute']);
var contextPath = 'http://localhost:8189/store'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'about-page.html',
        })
        .when('/books', {
            templateUrl: 'book-store.html',
            controller: 'bookController'
        })
});

app.controller('bookController', function ($scope, $http, $routeParams) {
    function parseURL(params) {
        var result = "";
        if (params['minPrice']) {
            result += '&minPrice=' + params['minPrice'];
        }if (params['maxPrice']) {
            result  += '&maxPrice=' + params['maxPrice'];
        }if (params['minPublishYear']) {
            result  += '&minPublishYear=' +  params['minPublishYear'];
        }if (params['maxPublishYear']) {
            result  += '&maxPublishYear=' +  params['maxPublishYear'];
        }if (params['titlePart']) {
            result  += '&titlePart=' +  params['titlePart'];
        }
        $scope.genres?.forEach(genre => {
            if (params[genre]) {
                result += `&genre=${genre}`;
            }
        })
        return result;
    }

    $scope.form = {};
    fillTable = function (pageNumber, params) {
        if (pageNumber === undefined) {
            pageNumber = 1;
        }
        $http.get(contextPath + '/api/v1/books?page=' + pageNumber + parseURL(params))
            .then(function (response) {
                var range = [];
                for (var i = 0; i < response.data.book.totalPages; i++) {
                    range.push(i + 1);
                }
                $scope.BooksList = response.data.book.content;
                $scope.genres = response.data.genres;
                $scope.page = response.data.book;
                $scope.range = range;
            });
    };

    $scope.filterBooks = function (pageNumber) {
        fillTable(pageNumber, $scope.form);
    };

    fillTable($routeParams.page, $scope.form);
});