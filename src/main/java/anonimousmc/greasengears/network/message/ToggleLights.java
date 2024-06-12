package anonimousmc.greasengears.network.message;

import anonimousmc.greasengears.common.entity.vehicle.CarEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ToggleLights implements IMessage<ToggleLights>{
    @Override
    public void encode(ToggleLights message, PacketBuffer buffer) {}

    @Override
    public ToggleLights decode(PacketBuffer buffer) {
        return new ToggleLights();
    }

    @Override
    public void handle(ToggleLights message, Supplier<NetworkEvent.Context> supplier) {
        supplier.get().enqueueWork(()->{
            ServerPlayerEntity sender = supplier.get().getSender();
            if(sender.getVehicle() != null && sender.getVehicle() instanceof CarEntity && ((CarEntity)sender.getVehicle()).getProperties().getSeats().get(((CarEntity)sender.getVehicle()).getSeatTracker().getSeatIndex(sender.getUUID())).isDriverSeat()){
                ((CarEntity)sender.getVehicle()).toggleLights();
            }
        });
        supplier.get().setPacketHandled(true);
    }
}
