module com.bgconsole.desktop_engine {

    exports com.bgconsole.desktop_engine;
    exports com.bgconsole.desktop_engine.store;
    exports com.bgconsole.desktop_engine.desktop_services;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.kotlin;

    requires com.bgconsole.core;
    requires com.bgconsole.domain;
    requires kotlin.stdlib;

}