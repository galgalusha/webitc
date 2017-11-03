package webitc.gui.system;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.gui.TableModelIcon;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.JScrollTable;

class SystemTable
  extends JScrollTable
{
  private static final int ICON_SIZE = 100;
  private SystemTableListener mListener = null;
  private TableModelIcon mModel = new TableModelIcon(4);
  
  public SystemTable()
  {
    super(EnumTableID.UNKNOWN);
    DataUserInfo localDataUserInfo = DataMgr.getInstance().getLoginUser();
    int i = localDataUserInfo.getUserID();
    if (i != 0) {
      mModel.addData(new IconData(20, StrRes.getStr("IDS_SYSTEM_PASSWORD_MODIFY"), IconRes.getIcon(33)));
    }
    if ((i == 1) || (i == 0)) {
      mModel.addData(new IconData(21, StrRes.getStr("IDS_SYSTEM_SETUPUSER"), IconRes.getIcon(34)));
    }
    setModel(mModel);
    setColumnModel();
    setSelectionMode(0);
    setRowHeight(100);
    setColumnSelectionAllowed(true);
    setTableHeader(null);
    setShowHorizontalLines(false);
    setShowVerticalLines(false);
    changeSelection(0, 0, false, false);
    addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
      {
        if (paramAnonymousMouseEvent.getClickCount() == 2) {
          SystemTable.this.SelectDblClick(paramAnonymousMouseEvent);
        }
      }
    });
  }
  
  private void SelectDblClick(MouseEvent paramMouseEvent)
  {
    int i = getSelectedID();
    if (i == -1) {
      return;
    }
    fireButtonEvent(i);
  }
  
  public void columnSelectionChanged(ListSelectionEvent paramListSelectionEvent)
  {
    super.columnSelectionChanged(paramListSelectionEvent);
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    if (mListener != null) {
      mListener.notifyChangeSelect(getSelectedID());
    }
  }
  
  public int getSelectedID()
  {
    int i = getSelectedRow();
    int j = getSelectedColumn();
    if ((i == -1) || (j == -1)) {
      return -1;
    }
    IconData localIconData = (IconData)mModel.getValueAt(i, j);
    if (localIconData == null) {
      return -1;
    }
    int k = mID;
    return k;
  }
  
  public void scrollWndResized(Dimension paramDimension)
  {
    if (mModel == null) {
      return;
    }
    int i = mModel.getColumnCount();
    int j = (int)(paramDimension.getWidth() / 100.0D);
    if (j <= 0) {
      j = 1;
    }
    if (i == j) {
      return;
    }
    int k = getSelectedID();
    TableModelIcon localTableModelIcon = new TableModelIcon(j);
    Vector localVector1 = mModel.getDataVector();
    for (int m = 0; m < localVector1.size(); m++) {
      if (localVector1.get(m) != null)
      {
        Vector localVector2 = (Vector)localVector1.get(m);
        for (int n = 0; n < localVector2.size(); n++) {
          if (localVector2.get(n) != null) {
            localTableModelIcon.addData(localVector2.get(n));
          }
        }
      }
    }
    mModel = localTableModelIcon;
    setModel(mModel);
    setColumnModel();
    setSelectedID(k);
    if (mListener != null) {
      mListener.notifyChangeSelect(k);
    }
  }
  
  private void setColumnModel()
  {
    DefaultTableColumnModel localDefaultTableColumnModel = (DefaultTableColumnModel)getColumnModel();
    for (int i = 0; i < mModel.getColumnCount(); i++)
    {
      localDefaultTableColumnModel.getColumn(i).setCellRenderer(new IconRenderer());
      localDefaultTableColumnModel.getColumn(i).setPreferredWidth(100);
    }
  }
  
  public void setListener(SystemTableListener paramSystemTableListener)
  {
    mListener = paramSystemTableListener;
  }
  
  public void setSelectedID(int paramInt)
  {
    for (int i = 0; i < mModel.getRowCount(); i++) {
      for (int j = 0; j < mModel.getColumnCount(); j++)
      {
        IconData localIconData = (IconData)mModel.getValueAt(i, j);
        if (paramInt == mID)
        {
          SystemTableListener localSystemTableListener = mListener;
          mListener = null;
          changeSelection(i, j, false, false);
          mListener = localSystemTableListener;
          return;
        }
      }
    }
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    super.valueChanged(paramListSelectionEvent);
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    if (mListener != null) {
      mListener.notifyChangeSelect(getSelectedID());
    }
  }
  
  class IconRenderer
    extends DefaultTableCellRenderer
  {
    IconRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      super.setForeground(ColorRes.C_NO_SELECTED_F);
      super.setBackground(ColorRes.C_NO_SELECTED_B);
      if ((paramBoolean1 == true) && (paramObject != null)) {
        setBorder(BorderRes.getBorder(2));
      } else {
        setBorder(null);
      }
      if (paramObject != null)
      {
        SystemTable.IconData localIconData = (SystemTable.IconData)paramObject;
        setVerticalTextPosition(3);
        setHorizontalTextPosition(0);
        setHorizontalAlignment(0);
        setIconTextGap(2);
        setText(mName);
        setToolTipText(mName);
        setIcon(mIcon);
      }
      else
      {
        setText("");
        setToolTipText(null);
        setIcon(null);
      }
      return this;
    }
  }
  
  class IconData
  {
    public final int mID;
    public final ImageIcon mIcon;
    public final String mName;
    
    public IconData(int paramInt, String paramString, ImageIcon paramImageIcon)
    {
      mID = paramInt;
      mName = paramString;
      mIcon = paramImageIcon;
    }
  }
  
  static abstract interface SystemTableListener
  {
    public abstract void notifyChangeSelect(int paramInt);
  }
}
