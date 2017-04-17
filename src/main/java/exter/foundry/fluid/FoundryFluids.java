package exter.foundry.fluid;

import exter.foundry.config.FoundryConfig;
import net.minecraftforge.fluids.Fluid;

public class FoundryFluids
{
  static public Fluid liquid_iron;
  static public Fluid liquid_gold;
  static public Fluid liquid_copper;
  static public Fluid liquid_tin;
  static public Fluid liquid_bronze;
  static public Fluid liquid_electrum;
  static public Fluid liquid_invar;
  static public Fluid liquid_nickel;
  static public Fluid liquid_zinc;
  static public Fluid liquid_brass;
  static public Fluid liquid_silver;
  static public Fluid liquid_steel;
  static public Fluid liquid_cupronickel;
  static public Fluid liquid_lead;
  static public Fluid liquid_platinum;
  static public Fluid liquid_aluminium;
  static public Fluid liquid_alumina;
  static public Fluid liquid_chromium;
  static public Fluid liquid_signalum;
  static public Fluid liquid_lumium;
  static public Fluid liquid_enderium;
  static public Fluid liquid_rubber;
  
  static public void init()
  {
    liquid_iron = LiquidMetalRegistry.instance.registerLiquidMetal( "Iron", 1800, 15);
    liquid_gold = LiquidMetalRegistry.instance.registerLiquidMetal( "Gold", 1350, 15);
    liquid_copper = LiquidMetalRegistry.instance.registerLiquidMetal( "Copper", 1300, 15);
    liquid_tin = LiquidMetalRegistry.instance.registerLiquidMetal( "Tin", 550, 0);
    liquid_bronze = LiquidMetalRegistry.instance.registerLiquidMetal( "Bronze", 1200, 15);
    liquid_electrum = LiquidMetalRegistry.instance.registerLiquidMetal( "Electrum", 1350, 15);
    liquid_invar = LiquidMetalRegistry.instance.registerLiquidMetal( "Invar", 1780, 15);
    liquid_nickel = LiquidMetalRegistry.instance.registerLiquidMetal( "Nickel", 1750, 15);
    liquid_zinc = LiquidMetalRegistry.instance.registerLiquidMetal( "Zinc", 700, 0);
    liquid_brass = LiquidMetalRegistry.instance.registerLiquidMetal( "Brass", 1200, 15);
    liquid_silver = LiquidMetalRegistry.instance.registerLiquidMetal( "Silver", 1250, 15);
    liquid_steel = LiquidMetalRegistry.instance.registerLiquidMetal( "Steel", 1800, 15);
    liquid_cupronickel = LiquidMetalRegistry.instance.registerLiquidMetal( "Cupronickel", 1750, 15);
    liquid_lead = LiquidMetalRegistry.instance.registerLiquidMetal( "Lead", 650, 0);  
    liquid_platinum = LiquidMetalRegistry.instance.registerLiquidMetal( "Platinum", 2100, 15);
    liquid_aluminium = LiquidMetalRegistry.instance.registerLiquidMetal( "Aluminium", 900, 0);
    liquid_chromium = LiquidMetalRegistry.instance.registerLiquidMetal( "Chrome", 2200, 10);   
    liquid_signalum = LiquidMetalRegistry.instance.registerLiquidMetal( "Signalum", 1400, 12);
    liquid_lumium = LiquidMetalRegistry.instance.registerLiquidMetal( "Lumium", 1250, 15);
    liquid_enderium = LiquidMetalRegistry.instance.registerLiquidMetal( "Enderium", 1900, 12);
    if(!FoundryConfig.recipe_alumina_melts_to_aluminium)
    {
      liquid_alumina = LiquidMetalRegistry.instance.registerLiquidMetal( "Alumina", 2100, 12);
    }

    LiquidMetalRegistry.instance.registerLiquidMetal( "Manganese", 1550, 15);   
    LiquidMetalRegistry.instance.registerLiquidMetal( "Titanium", 2000, 15);
    liquid_rubber = LiquidMetalRegistry.instance.registerLiquidMetal( "Rubber", 460, 0);
    LiquidMetalRegistry.instance.registerLiquidMetal( "StainlessSteel", 1900, 15);
    LiquidMetalRegistry.instance.registerLiquidMetal( "Kanthal", 1900, 15);
    LiquidMetalRegistry.instance.registerLiquidMetal( "Nichrome", 1950, 15);    
  }
}
