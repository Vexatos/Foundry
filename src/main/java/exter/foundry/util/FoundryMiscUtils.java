package exter.foundry.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exter.foundry.api.FoundryAPI;
import exter.foundry.api.FoundryUtils;
import exter.foundry.api.recipe.matcher.IItemMatcher;
import exter.foundry.api.recipe.matcher.ItemStackMatcher;
import exter.foundry.item.FoundryItems;
import exter.foundry.item.ItemMold;
import exter.foundry.recipes.manager.CastingRecipeManager;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;


/**
 * Miscellaneous utility methods
 */
public class FoundryMiscUtils
{
  static public int divCeil(int a,int b)
  {
    return a / b + ((a % b == 0) ? 0 : 1);
  }

  static public String getItemOreDictionaryName(ItemStack stack)
  {
    for(String name:OreDictionary.getOreNames())
    {
      List<ItemStack> ores = OreDictionary.getOres(name);
      for(ItemStack i : ores)
      {
        if(i.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(i, stack))
        {
          return name;
        }
      }
    }
    return null;
  }

  static public Set<String> getAllItemOreDictionaryNames(ItemStack stack)
  {
    Set<String> result = new HashSet<String>();
    for(String name:OreDictionary.getOreNames())
    {
      List<ItemStack> ores = OreDictionary.getOres(name);
      for(ItemStack i : ores)
      {
        if(i.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(i, stack))
        {
          result.add(name);
        }
      }
    }
    return result;
  }

  static public ItemStack getModItemFromOreDictionary(String orename)
  {
    return getModItemFromOreDictionary(orename, 1);
  }

  static public ItemStack getModItemFromOreDictionary(String orename, int amount)
  {
    for(ItemStack is:OreDictionary.getOres(orename))
    {
        is = is.copy();
        is.stackSize = amount;
        return is;
    }
    return null;
  }
  
  /**
   * Register item in the ore dictionary only if it's not already registered.
   * @param name Ore Dictionary name.
   * @param stack Item to register.
   */
  static public void registerInOreDictionary(String name,ItemStack stack)
  {
    if(stack == null)
    {
      return;
    }
    if(!FoundryUtils.isItemInOreDictionary(name,stack))
    {
      OreDictionary.registerOre(name, stack);
    }
  }
    
  static public FluidStack drainFluidFromWorld(World world,BlockPos pos,boolean do_drain)
  {
    IBlockState state = world.getBlockState(pos);
    if(state.getBlock() instanceof IFluidBlock)
    {
      IFluidBlock fluid_block = (IFluidBlock)state.getBlock();
      if(!fluid_block.canDrain(world, pos))
      {
        return null;
      }
      return fluid_block.drain(world, pos, do_drain);
    }

    if(state.getMaterial() == Material.WATER && Integer.valueOf(0).equals(state.getValue(BlockLiquid.LEVEL)))
    {
      if(do_drain)
      {
        world.setBlockToAir(pos);
      }
      return new FluidStack(FluidRegistry.WATER,Fluid.BUCKET_VOLUME);
    }

    if(state.getMaterial() == Material.LAVA && Integer.valueOf(0).equals(state.getValue(BlockLiquid.LEVEL)))
    {
      if(do_drain)
      {
        world.setBlockToAir(pos);
      }
      return new FluidStack(FluidRegistry.LAVA,Fluid.BUCKET_VOLUME);
    }
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  static public void localizeTooltip(String key, List<String> tooltip)
  {
    for(String str:(new TextComponentTranslation(key)).getUnformattedText().split("//"))
    {
      tooltip.add(TextFormatting.GRAY + str);
    }
  }

  static public void registerCasting(ItemStack item,Fluid liquid_metal,int ingots,ItemMold.SubItem mold_meta)
  {
    registerCasting(item,new FluidStack(liquid_metal, FoundryAPI.FLUID_AMOUNT_INGOT * ingots),mold_meta,null);
  }

  static public void registerCasting(ItemStack item,Fluid liquid_metal,int ingots,ItemMold.SubItem mold_meta,IItemMatcher extra)
  {
    registerCasting(item,new FluidStack(liquid_metal, FoundryAPI.FLUID_AMOUNT_INGOT * ingots),mold_meta,extra);
  }

  static public void registerCasting(ItemStack item,FluidStack fluid,ItemMold.SubItem mold_meta,IItemMatcher extra)
  {
    if(item != null)
    {
      ItemStack mold = FoundryItems.mold(mold_meta);
      ItemStack extra_item = extra != null?extra.getItem():null;
      if(CastingRecipeManager.instance.findRecipe(new FluidStack(fluid.getFluid(),FoundryAPI.CASTER_TANK_CAPACITY), mold, extra_item) == null)
      {
        CastingRecipeManager.instance.addRecipe(new ItemStackMatcher(item), fluid, mold, extra);
      }
    }
  }

}
