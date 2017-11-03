package webitc.gui.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.PntState;
import webitc.data.point.VZone;
import webitc.gui.TableModelIcon;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.GraphicLibrary;
import webitc.gui.common.IconRes;
import webitc.gui.common.JScrollTable;

public class SimpleIconTable
  extends JScrollTable
  implements SimpleAcDisplayer
{
  private static final Font FONT_AC_NAME = new Font("Dialog", 0, 15);
  private static final Font FONT_SETTEMP = new Font("Dialog", 0, 15);
  private static final Font FONT_TEMP_UNIT = new Font("Dialog", 0, 10);
  private static final int HEIGHT = 70;
  private static final int WIDTH = 120;
  private int mLineNum = -1;
  SimpleAcDisplayerListener mListener = null;
  private TableModelIcon mModel = new TableModelIcon(4);
  
  public SimpleIconTable()
  {
    super(EnumTableID.UNKNOWN);
    setModel(mModel);
    setColumnModel();
    setSelectionMode(0);
    setColumnSelectionAllowed(true);
    setTableHeader(null);
    setRowHeight(70);
    setShowHorizontalLines(false);
    setShowVerticalLines(false);
    setShowGrid(false);
    setIntercellSpacing(new Dimension(0, 0));
    getScrollPane().getViewport().setBackground(ColorRes.C_NO_SELECTED_B);
  }
  
  public void columnSelectionChanged(ListSelectionEvent paramListSelectionEvent)
  {
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    if (mListener != null)
    {
      ID localID = getSelectedID();
      if (localID != null) {
        mListener.notifyPntSelected(localID);
      }
    }
  }
  
  public ID getSelectedID()
  {
    int i = getSelectedRow();
    int j = getSelectedColumn();
    if ((i != -1) && (j != -1) && (mModel.getRowCount() > i) && (mModel.getColumnCount() > j)) {
      return (ID)mModel.getValueAt(i, j);
    }
    return null;
  }
  
  public void moveRowFocusDerive(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    boolean bool = false;
    int i = paramInt1 / mLineNum;
    int j = paramInt2 / mLineNum;
    if ((i == j) && (!paramBoolean)) {
      return;
    }
    if (i > j) {
      bool = false;
    } else {
      bool = true;
    }
    super.moveRowFocus(paramInt1, bool);
  }
  
  public void refresh()
  {
    repaint();
  }
  
  public void scrollWndResized(Dimension paramDimension)
  {
    if (mModel == null) {
      return;
    }
    int i = mModel.getColumnCount();
    int j = (int)(paramDimension.getWidth() / 120.0D);
    if (j <= 0) {
      j = 1;
    }
    if (i == j) {
      return;
    }
    mLineNum = j;
    int k = getSelectedRow();
    int m = getSelectedColumn();
    ID localID;
    if ((k != -1) && (m != -1)) {
      localID = (ID)mModel.getValueAt(k, m);
    } else {
      localID = null;
    }
    TableModelIcon localTableModelIcon = new TableModelIcon(j);
    Vector localVector1 = mModel.getDataVector();
    for (int n = 0; n < localVector1.size(); n++) {
      if (localVector1.get(n) != null)
      {
        Vector localVector2 = (Vector)localVector1.get(n);
        for (int i1 = 0; i1 < localVector2.size(); i1++) {
          if (localVector2.get(i1) != null) {
            localTableModelIcon.addData((ID)localVector2.get(i1));
          }
        }
      }
    }
    mModel = localTableModelIcon;
    setModel(mModel);
    setColumnModel();
    setSelectedID(localID);
  }
  
  private void setColumnModel()
  {
    DefaultTableColumnModel localDefaultTableColumnModel = (DefaultTableColumnModel)getColumnModel();
    for (int i = 0; i < mModel.getColumnCount(); i++)
    {
      IconRenderer localIconRenderer = new IconRenderer();
      localDefaultTableColumnModel.getColumn(i).setCellRenderer(localIconRenderer);
      localDefaultTableColumnModel.getColumn(i).setPreferredWidth(120);
    }
  }
  
  public void setListener(SimpleAcDisplayerListener paramSimpleAcDisplayerListener)
  {
    mListener = paramSimpleAcDisplayerListener;
  }
  
  public boolean setRoot()
  {
    int i = (int)(getScrollWndSize().getWidth() / 120.0D);
    if (i <= 0) {
      i = 1;
    }
    mLineNum = i;
    mModel = new TableModelIcon(i);
    ID[] arrayOfID = DataMgr.getInstance().getLoginUser().getZoneIDs();
    for (int j = 0; j < arrayOfID.length; j++) {
      mModel.addData(arrayOfID[j]);
    }
    setModel(mModel);
    setColumnModel();
    if (arrayOfID.length > 0)
    {
      changeSelection(0, 0, false, false);
      return true;
    }
    return false;
  }
  
  public void setSelectedID(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    for (int i = 0; i < mModel.getRowCount(); i++) {
      for (int j = 0; j < mModel.getColumnCount(); j++) {
        if (paramID.equals((ID)mModel.getValueAt(i, j)))
        {
          SimpleAcDisplayerListener localSimpleAcDisplayerListener = mListener;
          mListener = null;
          changeSelection(i, j, false, false);
          mListener = localSimpleAcDisplayerListener;
          return;
        }
      }
    }
  }
  
  public boolean setZone(ID paramID)
  {
    int i = (int)(getScrollWndSize().getWidth() / 120.0D);
    if (i <= 0) {
      i = 1;
    }
    mLineNum = i;
    mModel = new TableModelIcon(i);
    VZone localVZone = DataMgr.getInstance().getZoneFromID(paramID);
    for (int j = 0; j < localVZone.getPntNum(); j++)
    {
      ID localID = localVZone.getPntID(j);
      mModel.addData(localID);
    }
    setModel(mModel);
    setColumnModel();
    if (localVZone.getPntNum() > 0)
    {
      changeSelection(0, 0, false, false);
      return true;
    }
    return false;
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    if (mListener != null)
    {
      ID localID = getSelectedID();
      if (localID != null) {
        mListener.notifyPntSelected(localID);
      }
    }
  }
  
  class IconRenderer
    extends DefaultTableCellRenderer
  {
    private ImageIcon mFanImage = null;
    private ID mId = null;
    private boolean mIsErr = false;
    private int mPntType = 5;
    private String mTemp = null;
    
    IconRenderer() {}
    
    private ImageIcon getDrvModeImage(ID paramID)
    {
      ImageIcon localImageIcon = getStateImage(paramID);
      if (localImageIcon != null) {
        return localImageIcon;
      }
      int i = SimpleCommon.getDrvMode(paramID);
      switch (i)
      {
      case 4096: 
        localImageIcon = null;
        break;
      case 8194: 
        localImageIcon = IconRes.getIcon(87);
        break;
      case 8193: 
        localImageIcon = IconRes.getIcon(88);
        break;
      case 8200: 
        localImageIcon = IconRes.getIcon(89);
        break;
      case 8196: 
        localImageIcon = IconRes.getIcon(90);
        break;
      case 8208: 
        localImageIcon = IconRes.getIcon(91);
        break;
      default: 
        localImageIcon = IconRes.getIcon(90);
      }
      return localImageIcon;
    }
    
    private ImageIcon getLightImage(ID paramID)
    {
      PntState localPntState = DataMgr.getInstance().getState(paramID);
      int i = SimpleCommon.getOnOff(fOnOffState);
      if (i == 4096) {
        return IconRes.getIcon(151);
      }
      return IconRes.getIcon(152);
    }
    
    private ImageIcon getStateImage(ID paramID)
    {
      int i = SimpleCommon.getState(paramID);
      if (i == 0)
      {
        mIsErr = false;
        return null;
      }
      if (i == 2)
      {
        mIsErr = true;
        return IconRes.getIcon(165);
      }
      if (i == 1)
      {
        mIsErr = true;
        return IconRes.getIcon(166);
      }
      mIsErr = false;
      return null;
    }
    
    private ImageIcon getSwitchImage(ID paramID)
    {
      PntState localPntState = DataMgr.getInstance().getState(paramID);
      int i = SimpleCommon.getOnOff(fOnOffState);
      if (i == 4096) {
        return IconRes.getIcon(157);
      }
      return IconRes.getIcon(158);
    }
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      if ((paramBoolean1 == true) && (paramObject != null))
      {
        setBorder(BorderRes.getBorder(2));
        super.setForeground(ColorRes.SIMPLE_MODE_LIST_SELECT_F);
        super.setBackground(ColorRes.SIMPLE_MODE_LIST_SELECT_B);
      }
      else
      {
        setBorder(null);
        super.setForeground(ColorRes.SIMPLE_MODE_LIST_NO_SELECT_F);
        super.setBackground(ColorRes.SIMPLE_MODE_LIST_NO_SELECT_B);
      }
      mId = ((ID)paramObject);
      if (mId != null)
      {
        String str1 = DataMgr.getInstance().getShortName(mId);
        String str2 = DataMgr.getInstance().getDetailName(mId);
        setToolTipText(str2);
        setText(str1);
        int i = SimpleCommon.getPntType(mId);
        mPntType = i;
        switch (i)
        {
        case 0: 
          setIcon(getZoneImage(mId));
          mFanImage = getStateImage(mId);
          break;
        case 1: 
          setIcon(IconRes.getIcon(86));
          mFanImage = getDrvModeImage(mId);
          break;
        case 2: 
          setIcon(IconRes.getIcon(141));
          mFanImage = getVentModeImage(mId);
          break;
        case 3: 
          setIcon(getLightImage(mId));
          mFanImage = getStateImage(mId);
          break;
        case 4: 
        case 5: 
        default: 
          setIcon(getSwitchImage(mId));
          mFanImage = getStateImage(mId);
        }
        mTemp = SimpleCommon.getSetTempStrNoUnit(mId);
      }
      else
      {
        setText("");
        setIcon(null);
        setToolTipText(null);
      }
      return this;
    }
    
    private ImageIcon getVentModeImage(ID paramID)
    {
      int i = SimpleCommon.getVentMode(paramID);
      ImageIcon localImageIcon = getStateImage(paramID);
      if (localImageIcon != null) {
        return localImageIcon;
      }
      switch (i)
      {
      case 4096: 
        localImageIcon = null;
        break;
      case 8448: 
        localImageIcon = IconRes.getIcon(142);
        break;
      case 8704: 
        localImageIcon = IconRes.getIcon(143);
        break;
      case 9216: 
        localImageIcon = IconRes.getIcon(144);
        break;
      default: 
        localImageIcon = IconRes.getIcon(144);
      }
      return localImageIcon;
    }
    
    private ImageIcon getZoneImage(ID paramID)
    {
      PntState localPntState = DataMgr.getInstance().getState(paramID);
      int i = SimpleCommon.getOnOff(fOnOffState);
      if (i == 4096) {
        return IconRes.getIcon(117);
      }
      return IconRes.getIcon(118);
    }
    
    public void paint(Graphics paramGraphics)
    {
      if (mId == null) {
        return;
      }
      Color localColor = paramGraphics.getColor();
      Icon localIcon = getIcon();
      ImageIcon localImageIcon = mFanImage;
      int i = localIcon.getIconWidth();
      int j = localIcon.getIconHeight();
      int k = getWidth();
      int m = getHeight();
      int n = (k - i) / 2;
      int i1 = (m - j) / 2;
      paramGraphics.setColor(getBackground());
      paramGraphics.fillRect(0, 0, k, m);
      localIcon.paintIcon(this, paramGraphics, n, 0);
      if (!mIsErr)
      {
        int i2;
        Rectangle localRectangle2;
        if (mPntType == 0)
        {
          localObject = IconRes.getIcon(175);
          ((Icon)localObject).paintIcon(this, paramGraphics, n, 0);
          i2 = n + 7;
          localRectangle2 = new Rectangle(i2, 2, k, m);
          GraphicLibrary.drawTempString(paramGraphics, localRectangle2, getForeground(), SimpleIconTable.FONT_SETTEMP, SimpleIconTable.FONT_TEMP_UNIT, mTemp, 1);
        }
        else if (mPntType == 1)
        {
          localObject = IconRes.getIcon(92);
          ((Icon)localObject).paintIcon(this, paramGraphics, n, 0);
          i2 = n + 63;
          localRectangle2 = new Rectangle(i2, 2, k - i2, m);
          GraphicLibrary.drawTempString(paramGraphics, localRectangle2, getForeground(), SimpleIconTable.FONT_SETTEMP, SimpleIconTable.FONT_TEMP_UNIT, mTemp, 1);
        }
      }
      if (localImageIcon != null) {
        localImageIcon.paintIcon(this, paramGraphics, n, 0);
      }
      Object localObject = getText();
      if (localObject != null)
      {
        Rectangle localRectangle1 = new Rectangle(5, j + 2, k - 10, m - j - 2 - 10);
        GraphicLibrary.drawString(paramGraphics, localRectangle1, 1, getForeground(), SimpleIconTable.FONT_AC_NAME, (String)localObject, 2, false, true);
      }
      if (getBorder() != null) {
        getBorder().paintBorder(this, paramGraphics, 0, 0, k, m);
      }
      paramGraphics.setColor(localColor);
    }
  }
}
