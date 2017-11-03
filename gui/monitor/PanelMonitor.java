package webitc.gui.monitor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.Caret;
import webitc.common.FatalException;
import webitc.common.ListenerPntUpdate;
import webitc.common.StrRes;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.data.point.TargetErr;
import webitc.data.point.VAbst;
import webitc.frame.Job;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.frame.ThreadPntRequest;
import webitc.gui.ButtonEventListener;
import webitc.gui.common.CommonUse;
import webitc.gui.common.PanelAbstract;
import webitc.job.JobGetPntDetailInfo;
import webitc.job.JobGetZoneDetailInfo;
import webitc.job.JobOnOffPnt;
import webitc.job.JobSetPntState;
import webitc.job.JobUpdatePntStateDetail;
import webitc.job.JobUpdateZoneIDs;

public class PanelMonitor
  extends PanelAbstract
  implements ButtonEventListener, ListenerPntUpdate, AcDataSelectionListener
{
  GridBagLayout gridBagIconPanel = new GridBagLayout();
  GridBagLayout gridBagMain1 = new GridBagLayout();
  GridBagLayout gridBagMain2 = new GridBagLayout();
  private final PanelExample mExamplePanel = new PanelExample();
  private final JACTableIcon mIcon = new JACTableIcon();
  private final JPanel mIconPanel = new JPanel();
  private final JTextField mIconTxt = new JTextField();
  private final JACTableList mList = new JACTableList(EnumTableID.PANEL_MONITOR);
  private final JPanel mMainPanel = new JPanel();
  private final JMonitorBar mMonitorBar = new JMonitorBar();
  private final JOperationBar mOperationBar = new JOperationBar();
  private boolean mRootSelected = false;
  private ID mSelectedID = null;
  private ID mUpdateID = null;
  
  public PanelMonitor()
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
  
  public void acDataSelected(ID paramID)
  {
    if (getCurrentDisplayer() == mIcon) {
      mList.setSelectedPnt(paramID);
    } else if (getCurrentDisplayer() == mList) {
      mIcon.setSelectedPnt(paramID);
    }
    mOperationBar.updateButtons(paramID);
    mSelectedID = paramID;
    updateIconText();
  }
  
  private void addMonitorPanel(JComponent paramJComponent)
  {
    mMainPanel.add(paramJComponent, new GridBagConstraints(0, 0, 1, 2, 1.0D, 1.0D, 18, 1, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  public void buttonSelected(int paramInt)
  {
    Object localObject1;
    int i;
    Object localObject2;
    Result localResult;
    ID localID;
    Object localObject3;
    switch (paramInt)
    {
    case 4: 
      mMainPanel.remove(mList.getScrollPane());
      addMonitorPanel(mIconPanel);
      validate();
      mIcon.getScrollPane().repaint();
      mIconTxt.repaint();
      break;
    case 5: 
      mMainPanel.remove(mIconPanel);
      addMonitorPanel(mList.getScrollPane());
      validate();
      mList.getScrollPane().repaint();
      break;
    case 6: 
      localObject1 = DataMgr.getInstance().getLoginUser().getZoneIDArray();
      for (i = 0; i < ((ArrayList)localObject1).size(); i++) {
        if (ID.ZONE_ALL.equals(((ArrayList)localObject1).get(i)) == true)
        {
          localObject1 = new ArrayList();
          ((ArrayList)localObject1).add(ID.ZONE_ALL);
          break;
        }
      }
      localObject2 = new JobOnOffPnt((ArrayList)localObject1, true, 0);
      localResult = ThreadPntRequest.getInstance().addJob((Job)localObject2);
      if (CommonUse.waitJob(localResult)) {
        break;
      }
      break;
    case 7: 
      localObject1 = DataMgr.getInstance().getLoginUser().getZoneIDArray();
      for (i = 0; i < ((ArrayList)localObject1).size(); i++) {
        if (ID.ZONE_ALL.equals(((ArrayList)localObject1).get(i)) == true)
        {
          localObject1 = new ArrayList();
          ((ArrayList)localObject1).add(ID.ZONE_ALL);
          break;
        }
      }
      localObject2 = new JobOnOffPnt((ArrayList)localObject1, false, 0);
      localResult = ThreadPntRequest.getInstance().addJob((Job)localObject2);
      if (CommonUse.waitJob(localResult)) {
        break;
      }
      break;
    case 8: 
      localObject1 = getCurrentDisplayer();
      localID = ((AcDataDisplayer)localObject1).getSelectedPnt();
      if (localID != null)
      {
        localObject2 = new JobOnOffPnt(localID, true, 0);
        localResult = ThreadPntRequest.getInstance().addJob((Job)localObject2);
        if (CommonUse.waitJob(localResult)) {
          break;
        }
      }
      break;
    case 9: 
      localObject1 = getCurrentDisplayer();
      localID = ((AcDataDisplayer)localObject1).getSelectedPnt();
      if (localID != null)
      {
        localObject2 = new JobOnOffPnt(localID, false, 0);
        localResult = ThreadPntRequest.getInstance().addJob((Job)localObject2);
        if (CommonUse.waitJob(localResult)) {
          break;
        }
      }
      break;
    case 3: 
      if (mRootSelected == true) {
        updateZoneRoot(true);
      } else if (mUpdateID != null) {
        updateZone(mUpdateID, true);
      }
      break;
    case 10: 
      localObject1 = getCurrentDisplayer();
      localID = ((AcDataDisplayer)localObject1).getSelectedPnt();
      if (localID != null) {
        if (fType == 0)
        {
          localObject2 = new JobGetPntDetailInfo(localID);
          localResult = ThreadAppCom.getInstance().addJob((Job)localObject2);
          if (!CommonUse.waitJob(localResult)) {
            return;
          }
          localObject3 = new DlgInfo(((JobGetPntDetailInfo)localObject2).getPntInfo());
          ((DlgInfo)localObject3).doModal();
        }
        else if (fType == 1)
        {
          localObject2 = new JobGetZoneDetailInfo(localID);
          localResult = ThreadAppCom.getInstance().addJob((Job)localObject2);
          if (!CommonUse.waitJob(localResult)) {
            return;
          }
          localObject3 = new DlgInfo(((JobGetZoneDetailInfo)localObject2).getZoneInfo());
          ((DlgInfo)localObject3).doModal();
        }
      }
      break;
    case 11: 
      localObject1 = getCurrentDisplayer();
      localID = ((AcDataDisplayer)localObject1).getSelectedPnt();
      if (localID != null)
      {
        localObject2 = new JobUpdatePntStateDetail(localID);
        localResult = ThreadAppCom.getInstance().addJob((Job)localObject2);
        if (!CommonUse.waitJob(localResult)) {
          return;
        }
        localObject3 = DataMgr.getInstance();
        EnumPntType localEnumPntType = ((DataMgr)localObject3).getPntAbst(localID).getType();
        PntSet localPntSet = null;
        Object localObject4;
        Object localObject5;
        if (localEnumPntType == EnumPntType.ZONE)
        {
          localObject4 = new DlgSetInner(localEnumPntType, ((DataMgr)localObject3).getShortName(localID), ((DataMgr)localObject3).getDetailName(localID), ((DataMgr)localObject3).getDrvVentModeStr(localID), ((DataMgr)localObject3).getState(localID), ((DataMgr)localObject3).getPntTarget(localID, true), ((DataMgr)localObject3).getPntCurrent(localID));
          ((DlgSetInner)localObject4).doModal();
          if (((DlgSetInner)localObject4).isOK() == true) {
            localPntSet = ((DlgSetInner)localObject4).getPntSet();
          }
        }
        else if (localEnumPntType == EnumPntType.D3_INNER)
        {
          localObject4 = ((DataMgr)localObject3).getPntTarget(localID, true);
          if ((fValidVentMode != 0) || (fValidVentVol != 0))
          {
            localObject5 = new DlgSetVent(((DataMgr)localObject3).getShortName(localID), ((DataMgr)localObject3).getDetailName(localID), ((DataMgr)localObject3).getState(localID), (PntTarget)localObject4, ((DataMgr)localObject3).getPntCurrent(localID));
            ((DlgSetVent)localObject5).doModal();
            if (((DlgSetVent)localObject5).isOK() == true) {
              localPntSet = ((DlgSetVent)localObject5).getPntSet();
            }
          }
          else
          {
            localObject5 = new DlgSetInner(localEnumPntType, ((DataMgr)localObject3).getShortName(localID), ((DataMgr)localObject3).getDetailName(localID), ((DataMgr)localObject3).getDrvVentModeStr(localID), ((DataMgr)localObject3).getState(localID), (PntTarget)localObject4, ((DataMgr)localObject3).getPntCurrent(localID));
            ((DlgSetInner)localObject5).doModal();
            if (((DlgSetInner)localObject5).isOK() == true) {
              localPntSet = ((DlgSetInner)localObject5).getPntSet();
            }
          }
        }
        else
        {
          throw new FatalException("PanelMonitor.buttonSelected unsupported pnt type");
        }
        if ((localPntSet != null) && (localPntSet.hasOperation() == true))
        {
          localObject4 = new JobSetPntState(localID, localPntSet);
          localObject5 = ThreadPntRequest.getInstance().addJob((Job)localObject4);
          if (!CommonUse.waitJob((Result)localObject5)) {
            break;
          }
        }
      }
      break;
    }
  }
  
  public void exceptionalUpdated()
  {
    statusUpdated();
  }
  
  public AcDataDisplayer getCurrentDisplayer()
  {
    if (isAncestorOf(mIconPanel) == true) {
      return mIcon;
    }
    if (isAncestorOf(mList.getScrollPane()) == true) {
      return mList;
    }
    return null;
  }
  
  private void jbInit()
    throws Exception
  {
    try
    {
      mMonitorBar.addButtonEventListener(this);
      mOperationBar.addButtonEventListener(this);
      mList.setAcDataSelectionListener(this);
      mIcon.setAcDataSelectionListener(this);
      mList.addButtonEventListener(this);
      mIcon.addButtonEventListener(this);
      setLayout(gridBagMain1);
      mIconPanel.setLayout(gridBagIconPanel);
      mMainPanel.setLayout(gridBagMain2);
      mIconTxt.setEditable(false);
      mIconPanel.add(mIcon.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
      mIconPanel.add(mIconTxt, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
      add(mMonitorBar, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 18, 2, new Insets(1, 1, 1, 1), 0, 0));
      add(mMainPanel, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 18, 1, new Insets(1, 1, 1, 1), 0, 0));
      addMonitorPanel(mIconPanel);
      mMainPanel.add(mOperationBar, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 12, 0, new Insets(0, 3, 3, 3), 0, 0));
      mMainPanel.add(mExamplePanel, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 16, 0, new Insets(3, 3, 5, 3), 0, 0));
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void rootSelected()
  {
    if (!updateZoneRoot(true)) {
      return;
    }
    mList.showRoot();
    mIcon.showRoot();
    AcDataDisplayer localAcDataDisplayer = getCurrentDisplayer();
    mUpdateID = null;
    mRootSelected = true;
    if (localAcDataDisplayer != null)
    {
      mSelectedID = localAcDataDisplayer.getSelectedPnt();
      mOperationBar.updateButtons(mSelectedID);
      updateIconText();
    }
  }
  
  public void statusUpdated()
  {
    mList.refresh();
    mIcon.refresh();
    updateIconText();
    mOperationBar.updateButtons(mSelectedID);
    mMonitorBar.refresh();
  }
  
  private void updateIconText()
  {
    if (mSelectedID != null)
    {
      DataMgr localDataMgr = DataMgr.getInstance();
      PntState localPntState = localDataMgr.getState(mSelectedID);
      if (fTargetState.isNormal() == true) {
        mIconTxt.setText(localDataMgr.getSetTempStr(mSelectedID) + "  " + localDataMgr.getDrvVentModeStr(mSelectedID) + "  " + localDataMgr.getDetailName(mSelectedID));
      } else {
        mIconTxt.setText(StrRes.getStr("IDS_COMMON_ERR_CODE") + fTargetState.getErrCodeStr() + "  " + localDataMgr.getDetailName(mSelectedID));
      }
      mIconTxt.getCaret().setDot(0);
    }
    else
    {
      mIconTxt.setText("");
    }
  }
  
  public boolean updateZone(ID paramID, boolean paramBoolean)
  {
    JobUpdateZoneIDs localJobUpdateZoneIDs = new JobUpdateZoneIDs(paramID, paramBoolean);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdateZoneIDs);
    boolean bool = CommonUse.waitJob(localResult);
    if (localJobUpdateZoneIDs.isCommErrorOccured() == true) {
      bool = false;
    }
    return bool;
  }
  
  public boolean updateZoneRoot(boolean paramBoolean)
  {
    JobUpdateZoneIDs localJobUpdateZoneIDs = new JobUpdateZoneIDs(paramBoolean);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdateZoneIDs);
    boolean bool = CommonUse.waitJob(localResult);
    if (localJobUpdateZoneIDs.isCommErrorOccured() == true) {
      bool = false;
    }
    return bool;
  }
  
  public void zoneSelected(ID paramID)
  {
    if (!updateZone(paramID, true)) {
      return;
    }
    mList.showZone(paramID);
    mIcon.showZone(paramID);
    AcDataDisplayer localAcDataDisplayer = getCurrentDisplayer();
    mUpdateID = paramID;
    mRootSelected = false;
    if (localAcDataDisplayer != null)
    {
      mSelectedID = localAcDataDisplayer.getSelectedPnt();
      mOperationBar.updateButtons(mSelectedID);
      updateIconText();
    }
  }
}
