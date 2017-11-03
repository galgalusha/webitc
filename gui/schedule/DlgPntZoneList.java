package webitc.gui.schedule;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import webitc.common.StrRes;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PropPntCommon;
import webitc.data.point.VPoint;
import webitc.gui.common.BorderRes;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.ZoneTable;

class DlgPntZoneList
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  ButtonGroup mGrpPntKind = new ButtonGroup();
  JPanel mPnlCondition = new JPanel();
  JPanel mPnlOKCancel = new JPanel();
  JRadioButton mRdoAllPnt = new JRadioButton();
  private boolean mRdoSelected = false;
  JRadioButton mRdoUnreg = new JRadioButton();
  JRadioButton mRdoZone = new JRadioButton();
  private ID mSelectedID = null;
  ZoneTable mTable = new ZoneTable(EnumTableID.DLG_PNT_ZONE_LIST);
  
  public DlgPntZoneList(ID paramID)
  {
    try
    {
      jbInit();
      partsInit(paramID);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void changeTableSelect()
  {
    if (!mRdoSelected)
    {
      mSelectedID = mTable.getSelectedID();
      mBtnOK.setEnabled(true);
    }
  }
  
  public void errorPerformed()
  {
    mTable.setEnabled(false);
    mRdoAllPnt.setEnabled(false);
    mRdoZone.setEnabled(false);
    mRdoUnreg.setEnabled(false);
    mBtnOK.setEnabled(false);
  }
  
  public ID getSelectedID()
  {
    return mTable.getSelectedID();
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SCHDEVENTSETUP_TARGET"));
    getContentPane().setLayout(gridBagLayout1);
    mPnlCondition.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_DISP_CONDITION")));
    mPnlCondition.setLayout(gridBagLayout2);
    mRdoAllPnt.setMargin(new Insets(0, 2, 0, 2));
    mRdoAllPnt.setText(StrRes.getStr("IDS_COMMON_GROUP"));
    mRdoAllPnt.addActionListener(new DlgPntZoneList_mRdoAllPnt_actionAdapter(this));
    mRdoZone.setActionCommand("");
    mRdoZone.setMargin(new Insets(0, 2, 0, 2));
    mRdoZone.setText(StrRes.getStr("IDS_COMMON_ZONE"));
    mRdoZone.addActionListener(new DlgPntZoneList_mRdoZone_actionAdapter(this));
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgPntZoneList_mBtnOK_actionAdapter(this));
    mPnlOKCancel.setLayout(gridBagLayout3);
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgPntZoneList_mBtnCancel_actionAdapter(this));
    mRdoUnreg.setMargin(new Insets(0, 2, 0, 2));
    mRdoUnreg.setText(StrRes.getStr("IDS_COMMON_UNREGISTERD"));
    mRdoUnreg.addActionListener(new DlgPntZoneList_mRdoUnreg_actionAdapter(this));
    getContentPane().add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 2, 1, 1.0D, 1.0D, 18, 1, new Insets(3, 3, 3, 3), 0, 0));
    getContentPane().add(mPnlCondition, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 3, 0, 3), 0, 0));
    mPnlCondition.add(mRdoAllPnt, new GridBagConstraints(0, 0, 1, 1, 0.5D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlCondition.add(mRdoUnreg, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlCondition.add(mRdoZone, new GridBagConstraints(1, 0, 1, 1, 0.5D, 0.0D, 14, 0, new Insets(0, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.0D, 14, 0, new Insets(0, 0, 6, 12), 0, 0));
    mGrpPntKind.add(mRdoAllPnt);
    mGrpPntKind.add(mRdoZone);
    mPnlOKCancel.add(mBtnOK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlOKCancel.add(mBtnCancel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 5, 0, 0), 0, 0));
    mGrpPntKind.add(mRdoUnreg);
  }
  
  void mBtnCancel_actionPerformed()
  {
    closeDialog(false);
  }
  
  void mBtnOK_actionPerformed()
  {
    closeDialog(true);
  }
  
  void mRdoAllPnt_actionPerformed()
  {
    mRdoSelected = true;
    DataMgr localDataMgr = DataMgr.getInstance();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localDataMgr.getPntNum(); i++)
    {
      VPoint localVPoint = (VPoint)localDataMgr.getPnt(i);
      if (getCommonPropfPntType != EnumPntType.D3_DI) {
        localArrayList.add(localVPoint.getID());
      }
    }
    mTable.setZoneList(localArrayList);
    if (mSelectedID.fType == 0)
    {
      mTable.setSelectedID(mSelectedID);
      mBtnOK.setEnabled(true);
    }
    else
    {
      mTable.setSelectedID(null);
      mBtnOK.setEnabled(false);
    }
    mRdoSelected = false;
  }
  
  void mRdoUnreg_actionPerformed()
  {
    mRdoSelected = true;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(ID.ID_UNREG);
    mTable.setZoneList(localArrayList);
    if (mSelectedID.fType == 2)
    {
      mTable.setSelectedID(mSelectedID);
      mBtnOK.setEnabled(true);
    }
    else
    {
      mTable.setSelectedID(null);
      mBtnOK.setEnabled(false);
    }
    mRdoSelected = false;
  }
  
  void mRdoZone_actionPerformed()
  {
    mRdoSelected = true;
    DataMgr localDataMgr = DataMgr.getInstance();
    mTable.setZoneList(localDataMgr.getZoneIDList());
    if (mSelectedID.fType == 1)
    {
      mTable.setSelectedID(mSelectedID);
      mBtnOK.setEnabled(true);
    }
    else
    {
      mTable.setSelectedID(null);
      mBtnOK.setEnabled(false);
    }
    mRdoSelected = false;
  }
  
  private void partsInit(ID paramID)
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    mSelectedID = paramID;
    if (fType == 0)
    {
      if (localDataMgr.isExist(paramID) == true)
      {
        mRdoAllPnt.setSelected(true);
        mRdoAllPnt_actionPerformed();
        mTable.setSelectedID(paramID);
      }
      else
      {
        mRdoUnreg.setSelected(true);
        mRdoUnreg_actionPerformed();
        mTable.setSelectedID(ID.ID_UNREG);
      }
    }
    else if (fType == 1)
    {
      if (localDataMgr.isExist(paramID) == true)
      {
        mRdoZone.setSelected(true);
        mRdoZone_actionPerformed();
        mTable.setSelectedID(paramID);
      }
      else
      {
        mRdoUnreg.setSelected(true);
        mRdoUnreg_actionPerformed();
        mTable.setSelectedID(ID.ID_UNREG);
      }
    }
    else if (fType == 2)
    {
      mSelectedID = ID.ZONE_ALL;
      mRdoZone.setSelected(true);
      mRdoZone_actionPerformed();
      mTable.setSelectedID(ID.ZONE_ALL);
    }
    else
    {
      mRdoUnreg.setSelected(true);
      mRdoUnreg_actionPerformed();
      mTable.setSelectedID(ID.ID_UNREG);
    }
    ListSelectionModel localListSelectionModel = mTable.getSelectionModel();
    localListSelectionModel.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (!paramAnonymousListSelectionEvent.getValueIsAdjusting()) {
          DlgPntZoneList.this.changeTableSelect();
        }
      }
    });
  }
}
