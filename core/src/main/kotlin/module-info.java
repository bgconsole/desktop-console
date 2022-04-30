module com.bgconsole.core {
    exports com.bgconsole.core.project;
    exports com.bgconsole.core.profile;
    exports com.bgconsole.core.common;
    exports com.bgconsole.core.version;
    exports com.bgconsole.core.workspace;

    requires com.bgconsole.domain;
    requires kotlin.stdlib;

}