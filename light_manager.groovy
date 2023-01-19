definition(
    name: "Light Manager New",
    namespace: "lightManager",
    author: "Josh Lobe",
    description: "Manage my light status on the dashboard.",
    category: "Convenience",
    iconUrl: "",
    iconX2Url: ""
)

preferences {
    page(name: "mainPage", title: "Light Manager", install: true, uninstall: true) {
        section {
            input name: "lightDevices", type: "capability.switch", title: "When these lights are switched...", multiple: true
        }
    }
}

def installed() {
    log.trace "installed()"
}

def updated() {
    log.trace "updated()"
    unsubscribe()
    subscribe( lightDevices, "switch", "lightManagerSwitchHandler", [filterEvents: false] )
}

def lightManagerSwitchHandler(evt) {
    //log.trace evt.device
    //log.trace evt.value
    //log.debug evt
    //log.debug "Light Devices..."
    //log.debug lightDevices
    //log.debug evt.properties
    
    inner = ""
    lightDevices.each {
        inner += "<div>${it.label} (${it.currentValue('switch')})</div>"
    }
    html = "<div>"
    html += inner
    html += "</div>"
}

def uninstalled() {}
