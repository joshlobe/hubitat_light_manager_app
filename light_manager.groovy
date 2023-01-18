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
            // I've set my input.  I know I can "listen" for changes to any devices selected from front end via subscribe()
            // I have 5 switches I'd like to monitor
            // At this point, I do not know the status of any of the five devices
        }
    }
}

def installed() {
    log.trace "installed()"
}

def updated() {
    log.trace "updated()"
    unsubscribe()
    subscribe( lightDevices, "switch.on", "mySwitchOnHandler", [filterEvents: false] )
    // I've subscribed to the input above
}

def mySwitchOnHandler(evt) {
    log.debug evt
    // This logs a generic object
    log.debug evt.device
    // Gives me the string "Josh Bedroom Lights"
    log.debug evt.value
    // Give me the string of "on"
    log.debug lightDevices
    // This gives me a comma separated list of device names
    // i.e. [Living Room Ceiling Fan, Living Room Lights, Dining Room Lights, Bedroom Josh Lights, Bedroom Office]
    
    // NOW WHAT??
    // How do I query the status of the other four devices from here?
}

def uninstalled() {}
