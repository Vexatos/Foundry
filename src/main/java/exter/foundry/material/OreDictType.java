package exter.foundry.material;

public class OreDictType
{
  public final String prefix;
  public final String default_suffix;

  public final String name;

  private OreDictType(String type_prefix,String type_default_suffix,String type_name)
  {
    prefix = type_prefix;
    default_suffix = type_default_suffix;
    name = type_name;
  }

  static public final OreDictType[] TYPES = new OreDictType[] {
    new OreDictType("ingot","Iron","Ingot"),
    new OreDictType("dust","Redstone", "Dust"),
    new OreDictType("nugget","Gold", "Nugget"),
    new OreDictType("block","Iron", "Block"),
    new OreDictType("dustTiny","Iron", "TinyDust"),
    new OreDictType("dustSmall","Iron", "SmallDust"),
    new OreDictType("plate","Iron", "Plate"),
    new OreDictType("gear","Stone", "Gear")
  };
}
