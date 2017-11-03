package webitc.gui.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;

public class JScrollList
  extends JList
{
  private int mPreIndex = 0;
  private final ScrollListUI mScrollListUI = new ScrollListUI();
  private final JScrollPane mScrollPane = new JScrollPane();
  
  public JScrollList(Object[] paramArrayOfObject)
  {
    super(paramArrayOfObject);
    init();
  }
  
  public JScrollList(ListModel paramListModel)
  {
    super(paramListModel);
    init();
  }
  
  public JScrollList()
  {
    init();
  }
  
  public JScrollPane getScrollPane()
  {
    return mScrollPane;
  }
  
  public Dimension getScrollWndSize()
  {
    return mScrollPane.getSize();
  }
  
  private void init()
  {
    mScrollPane.getViewport().add(this, null);
    mScrollPane.addComponentListener(new ResizeEvent());
    mScrollPane.setSize(2048, 1600);
    setSelectionMode(0);
    setUI(mScrollListUI);
    getSelectionModel().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting() == true) {
          return;
        }
        int i = getSelectedIndex();
        if (i > mPreIndex) {
          JScrollList.this.moveFocus(i, false);
        } else if (i < mPreIndex) {
          JScrollList.this.moveFocus(i, true);
        }
        mPreIndex = i;
      }
    });
  }
  
  private void moveFocus(int paramInt, boolean paramBoolean)
  {
    if ((paramInt < 0) || (paramInt >= getModel().getSize())) {
      return;
    }
    Rectangle localRectangle1 = getCellBounds(paramInt, paramInt);
    Rectangle localRectangle2 = mScrollPane.getViewport().getViewRect();
    if ((y + height < y + height) && (y > y)) {
      return;
    }
    JScrollBar localJScrollBar = mScrollPane.getVerticalScrollBar();
    if ((getModel().getSize() - 1 == paramInt) || (height == 0)) {
      mScrollPane.getViewport().scrollRectToVisible(localRectangle1);
    } else if (paramBoolean) {
      localJScrollBar.setValue(y);
    } else {
      localJScrollBar.setValue(y - (height - height));
    }
  }
  
  public void redraw()
  {
    getScrollPane().updateUI();
  }
  
  public void scrollWndResized(Dimension paramDimension) {}
  
  public void setShowHorizontalLines(boolean paramBoolean)
  {
    mScrollListUI.setShowHorizontalLines(paramBoolean);
  }
  
  class ScrollListUI
    extends BasicListUI
  {
    private boolean mShowHorizontalLines = false;
    
    ScrollListUI() {}
    
    protected void paintCell(Graphics paramGraphics, int paramInt1, Rectangle paramRectangle, ListCellRenderer paramListCellRenderer, ListModel paramListModel, ListSelectionModel paramListSelectionModel, int paramInt2)
    {
      super.paintCell(paramGraphics, paramInt1, paramRectangle, paramListCellRenderer, paramListModel, paramListSelectionModel, paramInt2);
      if (mShowHorizontalLines == true)
      {
        int i = paramListModel.getSize();
        paramGraphics.setColor(Color.GRAY);
        paramGraphics.drawLine(x, y + getRowHeight(paramInt1) - 1, x + width, y + getRowHeight(paramInt1) - 1);
      }
    }
    
    public void setShowHorizontalLines(boolean paramBoolean)
    {
      mShowHorizontalLines = paramBoolean;
    }
  }
  
  class ResizeEvent
    implements ComponentListener
  {
    ResizeEvent() {}
    
    public void componentHidden(ComponentEvent paramComponentEvent) {}
    
    public void componentMoved(ComponentEvent paramComponentEvent) {}
    
    public void componentResized(ComponentEvent paramComponentEvent)
    {
      scrollWndResized(paramComponentEvent.getComponent().getSize());
    }
    
    public void componentShown(ComponentEvent paramComponentEvent) {}
  }
}
