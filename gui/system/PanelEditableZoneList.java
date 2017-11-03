package webitc.gui.system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.ID;
import webitc.gui.common.BorderRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.ZoneTable;

public class PanelEditableZoneList
  extends PanelAbstract
  implements PanelVerticalButtons.SelectedListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  ZoneTable mTable = new ZoneTable(EnumTableID.PANEL_EDITABLE_ZONE_LIST);
  PanelVerticalButtons mUpDown = new PanelVerticalButtons(this, StrRes.getStr("IDS_COMMON_ORDER_UP"), StrRes.getStr("IDS_COMMON_ORDER_DOWN"), null, null);
  
  public PanelEditableZoneList()
  {
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addZone(ID paramID)
  {
    mTable.addRow(paramID);
  }
  
  public int getRowCount()
  {
    return mTable.getRowCount();
  }
  
  public ID getSelectedZoneID()
  {
    return mTable.getSelectedID();
  }
  
  public ID[] getZoneList()
  {
    int i = mTable.getRowCount();
    ID[] arrayOfID = new ID[i];
    for (int j = 0; j < i; j++) {
      arrayOfID[j] = ((ID)mTable.getValueAt(j, 0));
    }
    return arrayOfID;
  }
  
  private void jbInit()
    throws Exception
  {
    setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SYSTEM_WATCH_ZONE")));
    setLayout(gridBagLayout1);
    add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(10, 5, 10, 5), 0, 0));
    add(mUpDown, new GridBagConstraints(1, 0, 1, 1, 0.0D, 1.0D, 10, 0, new Insets(10, 0, 10, 5), 0, 0));
    mTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting() == true) {
          return;
        }
        if (mTable.getSelectedRow() <= 0) {
          mUpDown.setEnabled(1, false);
        } else {
          mUpDown.setEnabled(1, true);
        }
        if (mTable.getSelectedRow() >= mTable.getRowCount() - 1) {
          mUpDown.setEnabled(2, false);
        } else {
          mUpDown.setEnabled(2, true);
        }
      }
    });
    mUpDown.setEnabled(1, false);
    mUpDown.setEnabled(2, false);
  }
  
  public void notifyButton1Selected()
  {
    mTable.upRow();
  }
  
  public void notifyButton2Selected()
  {
    mTable.downRow();
  }
  
  public void removeAllRow()
  {
    mTable.removeAllRow();
  }
  
  public void removeZone(ID paramID)
  {
    mTable.removeRow(paramID);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mTable.setEnabled(paramBoolean);
    mUpDown.setEnabled(1, paramBoolean);
    mUpDown.setEnabled(2, paramBoolean);
  }
  
  public void setZoneList(ID[] paramArrayOfID)
  {
    mTable.setZoneList(paramArrayOfID);
  }
}
