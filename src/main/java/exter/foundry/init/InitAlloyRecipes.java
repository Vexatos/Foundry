package exter.foundry.init;

import exter.foundry.api.FoundryAPI;
import exter.foundry.api.recipe.matcher.IItemMatcher;
import exter.foundry.api.recipe.matcher.ItemStackMatcher;
import exter.foundry.api.recipe.matcher.OreMatcher;
import exter.foundry.block.FoundryBlocks;
import exter.foundry.config.FoundryConfig;
import exter.foundry.fluid.FoundryFluids;
import exter.foundry.fluid.LiquidMetalRegistry;
import exter.foundry.recipes.manager.AlloyFurnaceRecipeManager;
import exter.foundry.recipes.manager.AlloyMixerRecipeManager;
import exter.foundry.recipes.manager.AlloyingCrucibleRecipeManager;
import exter.foundry.recipes.manager.InfuserRecipeManager;
import exter.foundry.util.FoundryMiscUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class InitAlloyRecipes
{
  // Create recipes for all alloy making machines.
  static private void addSimpleAlloy(String output,String input_a,int amount_a,String input_b,int amount_b)
  {
    ItemStack alloy_ingot = FoundryMiscUtils.getModItemFromOreDictionary("ingot" + output, amount_a + amount_b);
    if(alloy_ingot != null)
    {
      AlloyFurnaceRecipeManager.instance.addRecipe(
          alloy_ingot,
          new IItemMatcher[] {
              new OreMatcher("ingot" + input_a, amount_a),
              new OreMatcher("dust" + input_a, amount_a) },
          new IItemMatcher[] {
              new OreMatcher("ingot" + input_b, amount_b),
              new OreMatcher("dust" + input_b, amount_b) }
          );
    }
    
    Fluid fluid_out = LiquidMetalRegistry.instance.getFluid(output);
    Fluid fluid_in_a = LiquidMetalRegistry.instance.getFluid(input_a);
    Fluid fluid_in_b = LiquidMetalRegistry.instance.getFluid(input_b);

    AlloyingCrucibleRecipeManager.instance.addRecipe(
        new FluidStack(fluid_out, (amount_a + amount_b) * 3),
        new FluidStack(fluid_in_a, amount_a * 3),
        new FluidStack(fluid_in_b, amount_b * 3));

    AlloyMixerRecipeManager.instance.addRecipe(
        new FluidStack(fluid_out, amount_a + amount_b),
        new FluidStack[] {
          new FluidStack(fluid_in_a, amount_a),
          new FluidStack(fluid_in_b, amount_b)
          });
  }
  
  static public void init()
  {

    AlloyFurnaceRecipeManager.instance.addRecipe(
        new ItemStack(FoundryBlocks.block_refractory_glass),
        new ItemStackMatcher(Blocks.SAND),
        new ItemStackMatcher(Items.CLAY_BALL));

    
    addSimpleAlloy("Bronze",
        "Copper", 3,
        "Tin", 1);

    addSimpleAlloy("Brass",
        "Copper", 3,
        "Zinc", 1);
    
    addSimpleAlloy("Invar",
        "Iron", 2,
        "Nickel", 1);

    addSimpleAlloy("Electrum",
        "Gold", 1,
        "Silver", 1);

    addSimpleAlloy("Cupronickel",
        "Copper", 1,
        "Nickel", 1);
    
    Fluid liquid_redstone = FluidRegistry.getFluid("liquidredstone");
    Fluid liquid_glowstone = FluidRegistry.getFluid("liquidglowstone");
    Fluid liquid_enderpearl = FluidRegistry.getFluid("liquidenderpearl");

    if(liquid_redstone != null) {
      AlloyMixerRecipeManager.instance.addRecipe(
          new FluidStack(FoundryFluids.liquid_signalum, FoundryAPI.FLUID_AMOUNT_INGOT),
          new FluidStack[] {
              new FluidStack(FoundryFluids.liquid_copper, FoundryAPI.FLUID_AMOUNT_INGOT / 4/3),
              new FluidStack(FoundryFluids.liquid_silver, FoundryAPI.FLUID_AMOUNT_INGOT / 4),
              new FluidStack(liquid_redstone, 250)
          });
    }

    if(liquid_glowstone != null) {
      AlloyMixerRecipeManager.instance.addRecipe(
          new FluidStack(FoundryFluids.liquid_lumium, FoundryAPI.FLUID_AMOUNT_INGOT),
          new FluidStack[] {
              new FluidStack(FoundryFluids.liquid_tin, FoundryAPI.FLUID_AMOUNT_INGOT / 4/3),
              new FluidStack(FoundryFluids.liquid_silver, FoundryAPI.FLUID_AMOUNT_INGOT / 4),
              new FluidStack(liquid_glowstone, 250)
          });
    }

    if(liquid_enderpearl != null) {
      AlloyMixerRecipeManager.instance.addRecipe(
          new FluidStack(FoundryFluids.liquid_enderium, FoundryAPI.FLUID_AMOUNT_INGOT),
          new FluidStack[] {
              new FluidStack(FoundryFluids.liquid_tin, FoundryAPI.FLUID_AMOUNT_INGOT / 2),
              new FluidStack(FoundryFluids.liquid_silver, FoundryAPI.FLUID_AMOUNT_INGOT / 4),
              new FluidStack(FoundryFluids.liquid_platinum, FoundryAPI.FLUID_AMOUNT_INGOT / 4),
              new FluidStack(liquid_enderpearl, 250)
          });
    }
    
    if(FoundryConfig.recipe_steel)
    {
      InfuserRecipeManager.instance.addRecipe(new FluidStack(FoundryFluids.liquid_steel,FoundryAPI.FLUID_AMOUNT_INGOT / 4), new FluidStack(FoundryFluids.liquid_iron,FoundryAPI.FLUID_AMOUNT_INGOT / 4), new OreMatcher("dustCoal"), 160000);
      InfuserRecipeManager.instance.addRecipe(new FluidStack(FoundryFluids.liquid_steel,FoundryAPI.FLUID_AMOUNT_INGOT / 12), new FluidStack(FoundryFluids.liquid_iron,FoundryAPI.FLUID_AMOUNT_INGOT / 12), new OreMatcher("dustCharcoal"), 160000);
      InfuserRecipeManager.instance.addRecipe(new FluidStack(FoundryFluids.liquid_steel,FoundryAPI.FLUID_AMOUNT_INGOT / 16), new FluidStack(FoundryFluids.liquid_iron,FoundryAPI.FLUID_AMOUNT_INGOT / 16), new OreMatcher("dustSmallCoal"), 40000);
      InfuserRecipeManager.instance.addRecipe(new FluidStack(FoundryFluids.liquid_steel,FoundryAPI.FLUID_AMOUNT_INGOT / 48), new FluidStack(FoundryFluids.liquid_iron,FoundryAPI.FLUID_AMOUNT_INGOT / 48), new OreMatcher("dustSmallCharcoal"), 40000);
    }
  }
}
