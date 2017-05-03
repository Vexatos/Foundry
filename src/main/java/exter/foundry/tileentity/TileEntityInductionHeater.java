package exter.foundry.tileentity;

import exter.foundry.api.FoundryAPI;
import exter.foundry.api.heatable.IHeatProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.Optional;

public class TileEntityInductionHeater extends TileEntityFoundryPowered
{
  private class HeatProvider implements IHeatProvider
  {
    @Override
    public int provideHeat(int max_heat)
    {
      if(max_heat > MAX_PROVIDE)
      {
        max_heat = MAX_PROVIDE;
      }
      return (int)useFoundryEnergy(max_heat * 3 / 2, true) * 2 / 3;
    }
  }
  
  private static int MAX_PROVIDE = TileEntityFoundryHeatable.getMaxHeatRecieve(350000,FoundryAPI.CRUCIBLE_BASIC_TEMP_LOSS_RATE);

  private HeatProvider heat_provider;
  
  public TileEntityInductionHeater()
  {
    super();    
    heat_provider = new HeatProvider();
  }

  @Override
  public int getSizeInventory()
  {
    return 0;
  }

  @Override
  protected void updateClient()
  {

  }
  
  @Override
  protected void updateServer()
  {
    super.updateServer();
  }

  @Override
  public FluidTank getTank(int slot)
  {
    return null;
  }

  @Override
  public int getTankCount()
  {
    return 0;
  }

  @Override
  public long getFoundryEnergyCapacity()
  {
    return 25000;
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack)
  {
    return false;
  }  
  


  @Override
  public boolean hasCapability(Capability<?> cap,EnumFacing facing)
  {
    return (cap == FoundryAPI.capability_heatprovider && facing == EnumFacing.UP) || super.hasCapability(cap, facing);
  }
  
  @Override
  public <T> T getCapability(Capability<T> cap, EnumFacing facing)
  {
    if(cap == FoundryAPI.capability_heatprovider && facing == EnumFacing.UP)
    {
      return FoundryAPI.capability_heatprovider.cast(heat_provider);
    }
    return super.getCapability(cap, facing);
  }

  @Optional.Method(modid = "IC2")
  @Override
  public int getSinkTier()
  {
    return 2;
  }
}
