module com.bgconsole.domain {

    requires kotlin.stdlib;
    exports com.bgconsole.domain;

    opens com.bgconsole.domain to com.fasterxml.jackson.databind;
}