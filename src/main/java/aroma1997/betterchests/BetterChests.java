/**
 * The code of BetterChests and all related materials like textures is copyrighted material.
 * It may only be redistributed or used for Commercial purposes with the permission of Aroma1997.
 * 
 * All Rights reserved (c) by Aroma1997
 * 
 * See https://github.com/Aroma1997/BetterChests/blob/master/LICENSE.md for more information.
 */

package aroma1997.betterchests;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import aroma1997.core.log.LogHelperPre;
import aroma1997.core.network.NetworkHelper;
import aroma1997.core.network.PacketHandler;
import aroma1997.core.util.AromaRegistry;
import aroma1997.core.util.ItemUtil;
import aroma1997.core.version.VersionCheck;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, dependencies = "required-after:Aroma1997Core")
public class BetterChests {

	@Instance(Reference.MOD_ID)
	public static BetterChests instance;

	@SidedProxy(clientSide = "aroma1997.betterchests.client.ClientProxy", serverSide = "aroma1997.betterchests.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs creativeTabBC = new CreativeTabBChest();

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = LogHelperPre.genNewLogger(Reference.MOD_ID);
		Configuration config = new Configuration(new File(new File(
				event.getModConfigurationDirectory(), "aroma1997"),
				Reference.MOD_ID + ".cfg"));
		config.load();
		AromaRegistry.register(BetterChestsItems.class);
		if (config.hasChanged()) {
			config.save();
		}
		EntityRegistry.registerGlobalEntityID(EntityBag.class,
				"betterchests:bag", EntityRegistry.findGlobalUniqueEntityId());
		new EventListener();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		VersionCheck
				.registerVersionChecker(Reference.MOD_ID, Reference.VERSION);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	public static ItemStack getHelpBook() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("book.betterchests:introduction");
		list.add("book.betterchests:chapter.general");
		list.add("book.betterchests:general.1");
		list.add("book.betterchests:general.2");
		list.add("book.betterchests:general.3");
		list.add("book.betterchests:chest.1");
		list.add("book.betterchests:bag.1");
		list.add("book.betterchests:bag.2");
		list.add("book.betterchests:hammer.chest.1");
		list.add("book.betterchests:hammer.bag.1");
		list.add("book.betterchests:hammer.chest.2");
		list.add("book.betterchests:upgrade.1");
		list.add("book.betterchests:chapter.upgrades");
		Upgrade.addBagBookDescription(list);
		list.add("book.betterchests:upgrade.filter.1");
		list.add("book.betterchests:upgrade.filter.2");
		list.add("book.betterchests:chapter.credits");
		list.add("book.betterchests:credits");
		ItemStack item = ItemUtil.getWrittenBook("book.betterchests:name",
				"Aroma1997", true, list.toArray(new String[list.size()]));
		item.getTagCompound().setString("id", "BetterChests");
		return item;
	}

}
