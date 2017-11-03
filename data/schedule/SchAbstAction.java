package webitc.data.schedule;

import webitc.data.AbstAction;

public abstract class SchAbstAction
  extends AbstAction
{
  public SchAbstAction() {}
  
  protected abstract Object clone();
}
