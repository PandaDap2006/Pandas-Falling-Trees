package me.pandadev.fallingtrees.network;

import com.google.gson.Gson;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import me.pandadev.fallingtrees.FallingTrees;
import me.pandadev.fallingtrees.config.ServerConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class ConfigPacket {
	public static void clientReceiver(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
		FallingTrees.setServerConfig(new Gson().fromJson(new String(buf.readByteArray()), ServerConfig.class));
	}

	public static void sendToPlayer(ServerPlayer player) {
		FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
		buf.writeByteArray(new Gson().toJson(FallingTrees.getServerConfig()).getBytes());
		NetworkManager.sendToPlayer(player, PacketHandler.CONFIG_PACKET_ID, buf);
	}
}
