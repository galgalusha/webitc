package webitc;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import webitc.gui.common.CommonUse;

class RestraintKeyEventDispatcher
  implements KeyEventDispatcher
{
  RestraintKeyEventDispatcher() {}
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (paramKeyEvent.getKeyCode() == 10) && (CommonUse.isWaitJob() == true);
  }
}
