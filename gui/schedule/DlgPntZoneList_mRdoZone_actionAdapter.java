package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgPntZoneList_mRdoZone_actionAdapter
  implements ActionListener
{
  DlgPntZoneList adaptee;
  
  DlgPntZoneList_mRdoZone_actionAdapter(DlgPntZoneList paramDlgPntZoneList)
  {
    adaptee = paramDlgPntZoneList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoZone_actionPerformed();
  }
}
