package net.dadamalda.create_compatible_storage;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.neoforged.bus.api.IEventBus;

public class MoonlightRegistration {
    public static void register(IEventBus bus) {
        RegHelper.startRegisteringFor(bus);
    }
}
