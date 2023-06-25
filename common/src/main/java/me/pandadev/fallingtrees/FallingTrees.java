package me.pandadev.fallingtrees;

import com.google.gson.Gson;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.targets.ArchitecturyTarget;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.pandadev.fallingtrees.client.renderer.TreeRenderer;
import me.pandadev.fallingtrees.entity.TreeEntity;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketBundlePacker;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class FallingTrees {
	public static final String MOD_ID = "fallingtrees";
	public static ConfigHolder<TreesConfig> configHolder;

	// Entity Registry
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, Registries.ENTITY_TYPE);

	public static final RegistrySupplier<EntityType<TreeEntity>> TREE_ENTITY = ENTITIES.register("tree", () ->
			EntityType.Builder.of(TreeEntity::new, MobCategory.MISC).sized(1f, 1f)
					.fireImmune().build("tree"));

	public static void init() {
		AutoConfig.register(TreesConfig.class, GsonConfigSerializer::new);
		configHolder = AutoConfig.getConfigHolder(TreesConfig.class);
		PlayerEvent.PLAYER_JOIN.register(player -> {
			AutoConfig.getConfigHolder(TreesConfig.class).load();
		});

		EntityDataSerializers.registerSerializer(FallingTrees.BLOCK_MAP);

		ENTITIES.register();
	}

	@Environment(EnvType.CLIENT)
	public static void clientInit() {
		EntityRendererRegistry.register(TREE_ENTITY, TreeRenderer::new);
	}

	public static final EntityDataSerializer<Map<BlockPos, BlockState>> BLOCK_MAP = new EntityDataSerializer<>() {
		@Override
		public void write(FriendlyByteBuf buffer, Map<BlockPos, BlockState> value) {
			buffer.writeMap(value, FriendlyByteBuf::writeBlockPos, (friendlyByteBuf, state) -> friendlyByteBuf.writeVarInt(Block.getId(state)));
		}

		@Override
		public Map<BlockPos, BlockState> read(FriendlyByteBuf buffer) {
			return buffer.readMap(FriendlyByteBuf::readBlockPos, buf -> Block.stateById(buf.readVarInt()));
		}

		@Override
		public Map<BlockPos, BlockState> copy(Map<BlockPos, BlockState> value) {
			return new HashMap<>(value);
		}
	};
}