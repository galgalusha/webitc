package webitc.gui.monitor;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumHCSel;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumTableID;
import webitc.data.point.PntInfo;
import webitc.data.point.TargetErr;
import webitc.data.point.ZoneInfo;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.StringRenderer;

public class DlgInfo
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtnClose = new JButton();
  JButton mBtnHistory = new JButton();
  private DefaultTableModel mModel;
  private PntInfo mPntInfo = null;
  JScrollTable mTable = new JScrollTable(EnumTableID.DLG_MONITOR_INFO);
  private ZoneInfo mZoneInfo = null;
  
  public DlgInfo(ZoneInfo paramZoneInfo)
  {
    try
    {
      jbInit();
      partsInit(paramZoneInfo);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public DlgInfo(PntInfo paramPntInfo)
  {
    try
    {
      jbInit();
      partsInit(paramPntInfo);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mBtnHistory.setEnabled(false);
    mTable.setEnabled(false);
  }
  
  private void initTable()
  {
    mTable.setRowHeight(20);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_ITEM");
    arrayOfString[1] = StrRes.getStr("IDS_COMMON_CONTENT");
    mModel = new DefaultTableModel(arrayOfString, 0);
    mTable.setModel(mModel);
    TableColumnModel localTableColumnModel = mTable.getColumnModel();
    localTableColumnModel.getColumn(0).setPreferredWidth(170);
    localTableColumnModel.getColumn(0).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(1).setPreferredWidth(150);
    localTableColumnModel.getColumn(1).setCellRenderer(new StringRenderer());
    mTable.getScrollPane().setPreferredSize(new Dimension(325, 330));
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_CCMAIN_INFO"));
    mTable.setSelectionMode(0);
    getContentPane().setLayout(gridBagLayout1);
    mBtnClose.setText(StrRes.getStr("IDS_COMMON_CLOSE"));
    mBtnClose.addActionListener(new DlgInfo_mBtnClose_actionAdapter(this));
    mBtnHistory.setText(StrRes.getStr("IDS_DETAIL_INFO_ERR_HISTORY"));
    mBtnHistory.addActionListener(new DlgInfo_mBtnHistory_actionAdapter(this));
    getContentPane().add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 2, 1, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mBtnClose, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 6, 12), 0, 0));
    getContentPane().add(mBtnHistory, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
  }
  
  void mBtnClose_actionPerformed()
  {
    closeDialog(false);
  }
  
  void mBtnHistory_actionPerformed()
  {
    Object localObject;
    if (mZoneInfo == null)
    {
      localObject = new DlgInfoErrHistPnt(mPntInfo.mErrorHistory);
      ((DlgInfoErrHistPnt)localObject).doModal();
    }
    else if (mPntInfo == null)
    {
      localObject = new DlgInfoErrHistZone(mZoneInfo.fErrorHistory);
      ((DlgInfoErrHistZone)localObject).doModal();
    }
  }
  
  private void partsInit(ZoneInfo paramZoneInfo)
  {
    mZoneInfo = paramZoneInfo;
    initTable();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_NAME");
    arrayOfString[1] = fShortName;
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_DISCRIPTION");
    arrayOfString[1] = fDetailName;
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_SYSZONECONF_INTERVAL_ON");
    if (fIntervalON != 0) {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
    } else {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
    }
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_SYSZONECONF_INTERVAL");
    if (fIntervalON != 0) {
      arrayOfString[1] = StrRes.getSecStr(fIntervalON, false);
    } else {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_REGISTERD_GROUPS");
    arrayOfString[1] = Integer.toString(fMemberNum);
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_SCH_SCHEDULE");
    if ((fAutoCtrlBits & 0x1) != 0) {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
    } else {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
    }
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_ILK_SETUP");
    if ((fAutoCtrlBits & 0x20) != 0) {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
    } else {
      arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
    }
    mModel.addRow(arrayOfString);
  }
  
  private void partsInit(PntInfo paramPntInfo)
  {
    mPntInfo = paramPntInfo;
    initTable();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_NAME");
    arrayOfString[1] = fShortName;
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_DISCRIPTION");
    arrayOfString[1] = fDetailName;
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_PNT_TYPE");
    if (fPntType == EnumPntType.D3_INNER) {
      arrayOfString[1] = fInnerType.getStr();
    } else {
      arrayOfString[1] = fPntType.getStr();
    }
    mModel.addRow(arrayOfString);
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_D3_ADDR");
    arrayOfString[1] = fAddrStr;
    mModel.addRow(arrayOfString);
    if (fPntType != EnumPntType.D3_DI)
    {
      arrayOfString[0] = StrRes.getStr("IDS_SCH_SCHEDULE");
      if ((fAutoCtrlBits & 0x1) != 0) {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
      } else {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
      }
      mModel.addRow(arrayOfString);
    }
    if (fInnerType == EnumInnerType.NORMAL)
    {
      if (SystemInfo.isHMO() == true)
      {
        arrayOfString[0] = StrRes.getStr("IDS_GCTRL_HMO_FUNC_NAME");
        if ((fAutoCtrlBits & 0x4) != 0) {
          arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
        } else {
          arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
        }
        mModel.addRow(arrayOfString);
      }
      if (SystemInfo.isTempLimit() == true)
      {
        arrayOfString[0] = StrRes.getStr("IDS_GCTRL_TL_FUNC_NAME");
        if ((fAutoCtrlBits & 0x8) != 0) {
          arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
        } else {
          arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
        }
        mModel.addRow(arrayOfString);
      }
      if (SystemInfo.isChangeOver() == true)
      {
        arrayOfString[0] = StrRes.getStr("IDS_GCTRL_ACO_FUNC_NAME");
        if ((fAutoCtrlBits & 0x10) != 0) {
          arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
        } else {
          arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
        }
        mModel.addRow(arrayOfString);
      }
    }
    if (fPntType != EnumPntType.D3_DI)
    {
      arrayOfString[0] = StrRes.getStr("IDS_ILK_SETUP");
      if ((fAutoCtrlBits & 0x20) != 0) {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
      } else {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
      }
      mModel.addRow(arrayOfString);
    }
    if ((fInnerType == EnumInnerType.NORMAL) || (fInnerType == EnumInnerType.VENT))
    {
      arrayOfString[0] = StrRes.getStr("IDS_COMMON_RC_TYPE");
      if (fChildMode == true) {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_RC_TYPE_SLAVE");
      } else {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_RC_TYPE_MASTER");
      }
      mModel.addRow(arrayOfString);
    }
    if (fInnerType == EnumInnerType.NORMAL)
    {
      arrayOfString[0] = StrRes.getStr("IDS_COMMON_COOL_HEAT_SEL");
      arrayOfString[1] = fCoolHeatOpt.getStr();
      mModel.addRow(arrayOfString);
    }
    if (fInnerType == EnumInnerType.NORMAL)
    {
      arrayOfString[0] = StrRes.getStr("IDS_COMMON_OU_ADDR");
      arrayOfString[1] = Integer.toString(fOuterAddr);
      mModel.addRow(arrayOfString);
    }
    if (!mError.isNormal())
    {
      arrayOfString[0] = StrRes.getStr("IDS_COMMON_ERR_CODE");
      arrayOfString[1] = mError.getErrCodeStr();
      mModel.addRow(arrayOfString);
      arrayOfString[0] = StrRes.getStr("IDS_COMMON_ERR_UNIT_NO");
      arrayOfString[1] = mError.getUnitNoStr();
      mModel.addRow(arrayOfString);
    }
  }
}
