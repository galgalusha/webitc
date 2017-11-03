package webitc.gui.schedule;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.VAbst;
import webitc.data.schedule.Daily;
import webitc.data.schedule.DefaultAction;
import webitc.data.schedule.SchEvent;
import webitc.gui.common.ITCTableCellRenderer;
import webitc.gui.common.JScrollTable;

class SchEventTable
  extends JScrollTable
{
  private DefaultTableModel mModel = new DefaultTableModel();
  
  protected SchEventTable(EnumTableID paramEnumTableID)
  {
    super(paramEnumTableID);
    setSelectionMode(0);
    mModel.addColumn(StrRes.getStr("IDS_UTILTIMEYMD_TIME"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_PNT_TYPE"));
    mModel.addColumn(StrRes.getStr("IDS_SCHDEVENTSETUP_TARGET"));
    mModel.addColumn(StrRes.getStr("IDS_WEBACSET_ONOFF"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_RC_RKK_ON_OFF"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_RC_RKK_MODE"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_RC_RKK_TEMP"));
    mModel.addColumn(StrRes.getStr("IDS_CCOPE_LABEL_SETPOINT"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_DRVMODE"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_VENT_MODE"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_VENT_VOL"));
    setModel(mModel);
    TableColumnModel localTableColumnModel = getColumnModel();
    int i = 0;
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(0));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(1));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(2));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(3));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(4));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(5));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(6));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(7));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(8));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(9));
    localTableColumnModel.getColumn(i++).setCellRenderer(new DefaultActionRenderer(10));
    i = 0;
    localTableColumnModel.getColumn(i++).setPreferredWidth(50);
    localTableColumnModel.getColumn(i++).setPreferredWidth(30);
    localTableColumnModel.getColumn(i++).setPreferredWidth(80);
    localTableColumnModel.getColumn(i++).setPreferredWidth(60);
    localTableColumnModel.getColumn(i++).setPreferredWidth(80);
    localTableColumnModel.getColumn(i++).setPreferredWidth(80);
    localTableColumnModel.getColumn(i++).setPreferredWidth(80);
    localTableColumnModel.getColumn(i++).setPreferredWidth(75);
    localTableColumnModel.getColumn(i++).setPreferredWidth(80);
    localTableColumnModel.getColumn(i++).setPreferredWidth(80);
    localTableColumnModel.getColumn(i++).setPreferredWidth(130);
    getScrollPane().setPreferredSize(new Dimension(350, 150));
  }
  
  protected void addEvent(SchEvent paramSchEvent, boolean paramBoolean)
  {
    if (mModel.getRowCount() >= 16) {
      throw new FatalException("SchEventTable.addAction");
    }
    for (int i = 0; i != mModel.getRowCount(); i++)
    {
      localObject = (SchEvent)mModel.getValueAt(i, 0);
      if (paramSchEvent.compareTo(localObject) < 0) {
        break;
      }
    }
    Object localObject = { paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent, paramSchEvent };
    mModel.insertRow(i, (Object[])localObject);
    if (paramBoolean == true) {
      setRowSelectionInterval(i, i);
    }
  }
  
  protected Daily createDaily()
  {
    Daily localDaily = new Daily();
    for (int i = 0; i < getEventCount(); i++) {
      localDaily.addEvent(getEvent(i));
    }
    return localDaily;
  }
  
  protected void deleteAllEvent()
  {
    mModel.setRowCount(0);
  }
  
  protected void deleteEvent()
  {
    int i = getSelectedRow();
    if (i == -1) {
      throw new FatalException("PanelActionSelect.deleteAction");
    }
    mModel.removeRow(i);
    if (mModel.getRowCount() != 0)
    {
      if (i == mModel.getRowCount()) {
        i--;
      }
      setRowSelectionInterval(i, i);
    }
  }
  
  protected SchEvent getEvent(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getEventCount())) {
      throw new ArrayIndexOutOfBoundsException("PanelActionSelect.getEvent");
    }
    return (SchEvent)mModel.getValueAt(paramInt, 0);
  }
  
  protected int getEventCount()
  {
    return mModel.getRowCount();
  }
  
  protected SchEvent getSelectedEvent()
  {
    int i = getSelectedRow();
    if (i != -1) {
      return (SchEvent)mModel.getValueAt(i, 0);
    }
    return null;
  }
  
  protected void setDaily(Daily paramDaily)
  {
    deleteAllEvent();
    for (int i = 0; i < paramDaily.getEventCount(); i++)
    {
      SchEvent localSchEvent = (SchEvent)paramDaily.getEvent(i).clone();
      addEvent(localSchEvent, false);
    }
    if (getRowCount() != 0) {
      setRowSelectionInterval(0, 0);
    }
  }
  
  protected void sortTable(int paramInt)
  {
    SchEvent localSchEvent1 = (SchEvent)mModel.getValueAt(paramInt, 0);
    if (localSchEvent1 == null) {
      throw new FatalException("PanelActionSelect.sortTable");
    }
    for (int i = 0; i != mModel.getRowCount(); i++) {
      if (paramInt != i)
      {
        SchEvent localSchEvent2 = (SchEvent)mModel.getValueAt(i, 0);
        if (localSchEvent1.compareTo(localSchEvent2) < 0)
        {
          if (paramInt >= i) {
            break;
          }
          i--;
          break;
        }
      }
    }
    if (i == mModel.getRowCount()) {
      i--;
    }
    mModel.moveRow(paramInt, paramInt, i);
    setRowSelectionInterval(i, i);
  }
  
  class DefaultActionRenderer
    extends ITCTableCellRenderer
  {
    public static final int DRVMODE = 8;
    public static final int ONOFF = 3;
    public static final int RKK_DRVMODE = 5;
    public static final int RKK_ONOFF = 4;
    public static final int RKK_SETTEMP = 6;
    public static final int SETTEMP = 7;
    public static final int SHORT_NAME = 2;
    public static final int TIME = 0;
    public static final int TYPE = 1;
    public static final int VENTMODE = 9;
    public static final int VENTVOL = 10;
    private final int mType;
    
    public DefaultActionRenderer(int paramInt)
    {
      mType = paramInt;
    }
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      setEnabled(paramJTable.isEnabled());
      setColor(paramBoolean1);
      SchEvent localSchEvent = (SchEvent)paramObject;
      DefaultAction localDefaultAction = (DefaultAction)localSchEvent.getAction();
      DataMgr localDataMgr = DataMgr.getInstance();
      Object localObject;
      switch (mType)
      {
      case 1: 
        localObject = localDefaultAction.getTargetID();
        if (fType == 2)
        {
          setText("U");
        }
        else
        {
          VAbst localVAbst = localDataMgr.getPntAbst((ID)localObject);
          if (getIDfType == 0) {
            setText("G");
          } else if (getIDfType == 1) {
            setText("Z");
          } else {
            setText("U");
          }
        }
        break;
      case 0: 
        localObject = new StringBuffer();
        if (localSchEvent.getHour() < 10) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(localSchEvent.getHour() + StrRes.getStr("IDS_COMMON_SEPARATER_COLON"));
        if (localSchEvent.getMin() < 10) {
          ((StringBuffer)localObject).append("0");
        }
        ((StringBuffer)localObject).append(localSchEvent.getMin());
        setText(((StringBuffer)localObject).toString());
        break;
      case 2: 
        setText(localDataMgr.getShortName(localDefaultAction.getTargetID()));
        break;
      case 3: 
        setText(localDefaultAction.getOnOffStr());
        break;
      case 7: 
        setText(localDefaultAction.getSetPointStr());
        break;
      case 8: 
        setText(localDefaultAction.getDrvModeStr());
        break;
      case 4: 
        setText(localDefaultAction.getRKKOnOffStr());
        break;
      case 5: 
        setText(localDefaultAction.getRKKDrvModeStr());
        break;
      case 6: 
        setText(localDefaultAction.getRKKSetTempStr());
        break;
      case 9: 
        setText(localDefaultAction.getVentModeStr());
        break;
      case 10: 
        setText(localDefaultAction.getVentVolStr());
      }
      return this;
    }
  }
}
