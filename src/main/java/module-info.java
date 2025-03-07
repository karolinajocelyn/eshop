module eshop.main {
    requires static lombok;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    exports id.ac.ui.cs.advprog.eshop.repository;
}