package webitc.gui.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class AnimationPanel
  extends PanelAbstract
{
  public static final int TEXT_ALIGN_CENTER = -1;
  public static final int TEXT_ALIGN_LEFT = -2;
  public static final int TEXT_ALIGN_RIGHT = -3;
  public static final int TIMER_DEFAULT = -1;
  public static final int TIMER_FAN_5 = 0;
  public static final int TIMER_NUM = 1;
  Image mBaseImage = null;
  boolean mBaseOnly = false;
  int mCurrentIndex = 0;
  boolean mDoLoop = true;
  Font mFont = new Font("Dialog", 0, 20);
  ArrayList mImageArray = new ArrayList();
  int mInterval = 1000;
  boolean mScaling = false;
  String mText = null;
  Color mTextColor = Color.RED;
  int mTextX = -1;
  int mTextY = 90;
  Timer mTimer = null;
  int mTimerID = -1;
  Color mTransparentColor = null;
  static TimerProperties[] sTimerArray = new TimerProperties[1];
  
  public AnimationPanel(Image paramImage, int paramInt)
  {
    mTimerID = paramInt;
    setBaseImage(paramImage);
  }
  
  public AnimationPanel(Image paramImage)
  {
    setBaseImage(paramImage);
  }
  
  public AnimationPanel(ImageIcon paramImageIcon, int paramInt)
  {
    mTimerID = paramInt;
    setBaseImage(paramImageIcon);
  }
  
  public AnimationPanel(ImageIcon paramImageIcon)
  {
    setBaseImage(paramImageIcon);
  }
  
  public AnimationPanel(int paramInt)
  {
    mTimerID = paramInt;
  }
  
  public AnimationPanel() {}
  
  public void addAnimationImage(ImageIcon paramImageIcon)
  {
    mImageArray.add(paramImageIcon.getImage());
  }
  
  public synchronized void beginAnimation()
  {
    mBaseOnly = false;
    if (mTimerID == -1)
    {
      if (mTimer == null)
      {
        mTimer = new Timer(mInterval, new TimerListener());
        mTimer.setInitialDelay(mInterval);
      }
      mCurrentIndex = 0;
      updateImage(0);
      mTimer.restart();
    }
    else
    {
      createTimerProperty(mTimerID);
    }
  }
  
  public void clearAnimationImage()
  {
    mImageArray.clear();
    mText = null;
  }
  
  private void createTimerProperty(int paramInt)
  {
    if (paramInt >= 1) {
      return;
    }
    if (sTimerArray[paramInt] == null) {
      sTimerArray[paramInt] = new TimerProperties();
    }
    if (sTimerArraymTimer == null)
    {
      if (sTimerArraymListener == null) {
        sTimerArraymListener = new TimerListener(this, sTimerArray[paramInt]);
      }
      sTimerArraymTimer = new Timer(mInterval, sTimerArraymListener);
    }
    else
    {
      sTimerArraymListener.addListener(this);
    }
    if (sTimerArraymTimer.isRunning() == true)
    {
      updateImage(sTimerArraymCurrentIndex);
    }
    else
    {
      updateImage(0);
      sTimerArraymTimer.start();
    }
  }
  
  public boolean doLoop()
  {
    return mDoLoop;
  }
  
  public synchronized void endAnimation()
  {
    if (mTimerID == -1)
    {
      if (mTimer != null) {
        mTimer.stop();
      }
      mCurrentIndex = 0;
    }
    else
    {
      if (sTimerArray[mTimerID] == null) {
        return;
      }
      if (sTimerArraymTimerID].mListener != null)
      {
        sTimerArraymTimerID].mListener.deleteListener(this);
        if (sTimerArraymTimerID].mListener.getListenerNum() == 0)
        {
          if (sTimerArraymTimerID].mTimer != null) {
            sTimerArraymTimerID].mTimer.stop();
          }
          sTimerArraymTimerID].mCurrentIndex = 0;
        }
      }
    }
  }
  
  public static void endAnimation(int paramInt)
  {
    if (paramInt == -1) {
      return;
    }
    if (paramInt >= 1) {
      return;
    }
    if (sTimerArray[paramInt] != null)
    {
      Timer localTimer = sTimerArraymTimer;
      if (localTimer != null)
      {
        localTimer.stop();
        TimerListener localTimerListener = sTimerArraymListener;
        if (localTimerListener != null) {
          mListener.clear();
        }
      }
      sTimerArray[paramInt] = null;
    }
  }
  
  public int getAnimationCount()
  {
    return mImageArray.size();
  }
  
  public Image getAnimationImage(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mImageArray.size())) {
      return null;
    }
    return (Image)mImageArray.get(paramInt);
  }
  
  public int getAnimationInterval()
  {
    return mInterval;
  }
  
  public Image getBaseImage()
  {
    return mBaseImage;
  }
  
  public Color getTransParentColor()
  {
    return mTransparentColor;
  }
  
  public boolean isBaseImageOnly()
  {
    return mBaseOnly;
  }
  
  public boolean isRunning()
  {
    if (mTimerID == -1)
    {
      if (mTimer == null) {
        return false;
      }
      return mTimer.isRunning();
    }
    Timer localTimer = sTimerArraymTimerID].mTimer;
    if (localTimer != null) {
      return localTimer.isRunning();
    }
    return false;
  }
  
  public boolean isScaling()
  {
    return mScaling;
  }
  
  public void paint(Graphics paramGraphics)
  {
    if (!isVisible()) {
      return;
    }
    Color localColor = paramGraphics.getColor();
    Font localFont = paramGraphics.getFont();
    paramGraphics.setColor(getBackground());
    paramGraphics.fillRect(0, 0, getSizewidth, getSizeheight);
    if (mBaseImage != null)
    {
      localImage = GraphicLibrary.getTransparentImage(mBaseImage, mTransparentColor, !isEnabled());
      if (mScaling == true) {
        localImage = GraphicLibrary.getScaleImage(localImage, new Dimension(getSizewidth, getSizeheight));
      }
      localObject = new ImageIcon(localImage);
      ((ImageIcon)localObject).paintIcon(this, paramGraphics, 0, 0);
    }
    if (mBaseOnly == true) {
      return;
    }
    if (mCurrentIndex >= mImageArray.size()) {
      return;
    }
    Image localImage = (Image)mImageArray.get(mCurrentIndex);
    if (localImage == null) {
      return;
    }
    Object localObject = GraphicLibrary.getTransparentImage(localImage, mTransparentColor, !isEnabled());
    if (mScaling == true) {
      localObject = GraphicLibrary.getScaleImage((Image)localObject, new Dimension(getSizewidth, getSizeheight));
    }
    ImageIcon localImageIcon = new ImageIcon((Image)localObject);
    localImageIcon.paintIcon(this, paramGraphics, 0, 0);
    if (mText != null)
    {
      if (mFont != null) {
        paramGraphics.setFont(mFont);
      }
      int i = mTextX;
      int j = getWidth();
      int k = paramGraphics.getFontMetrics().stringWidth(mText);
      if (mTextX == -1) {
        i = (j - k) / 2 - 5;
      } else if ((mTextX != -2) && (mTextX == -3)) {
        i = j - k;
      }
      if (mTextColor != null) {
        paramGraphics.setColor(mTextColor);
      }
      paramGraphics.drawString(mText, i, mTextY);
    }
    paramGraphics.setColor(localColor);
    paramGraphics.setFont(localFont);
  }
  
  public void setAnimationInterval(int paramInt)
  {
    mInterval = paramInt;
  }
  
  public void setBaseImage(Image paramImage)
  {
    mBaseImage = paramImage;
  }
  
  public void setBaseImage(ImageIcon paramImageIcon)
  {
    setBaseImage(paramImageIcon.getImage());
  }
  
  public void setBaseImageOnly(boolean paramBoolean)
  {
    mBaseOnly = paramBoolean;
    endAnimation();
  }
  
  public void setLoop(boolean paramBoolean)
  {
    mDoLoop = paramBoolean;
  }
  
  public void setScaling(boolean paramBoolean)
  {
    mScaling = paramBoolean;
  }
  
  public void setText(String paramString)
  {
    mText = paramString;
  }
  
  public void setText(String paramString, int paramInt1, int paramInt2)
  {
    mText = paramString;
    mTextX = paramInt1;
    if (paramInt2 < 0) {
      paramInt2 = 0;
    }
    mTextY = paramInt2;
  }
  
  public void setText(Color paramColor, Font paramFont, String paramString, int paramInt1, int paramInt2)
  {
    mTextColor = paramColor;
    mFont = paramFont;
    mText = paramString;
    mTextX = paramInt1;
    if (paramInt2 < 0) {
      paramInt2 = 0;
    }
    mTextY = paramInt2;
  }
  
  public void setTransparentColor(Color paramColor)
  {
    mTransparentColor = paramColor;
  }
  
  private void update()
  {
    Graphics localGraphics = getGraphics();
    if (localGraphics != null) {
      update(localGraphics);
    }
  }
  
  private void update4Timer(int paramInt)
  {
    if (paramInt >= mImageArray.size()) {
      paramInt = mImageArray.size() - 1;
    }
    mCurrentIndex = paramInt;
    update();
  }
  
  public void updateImage(int paramInt)
  {
    if (paramInt >= mImageArray.size()) {
      paramInt = mImageArray.size() - 1;
    }
    mCurrentIndex = paramInt;
    if ((mTimerID != -1) && (sTimerArray[mTimerID] != null)) {
      sTimerArraymTimerID].mCurrentIndex = paramInt;
    }
    update();
  }
  
  public void updateImage()
  {
    update();
  }
  
  class TimerListener
    implements ActionListener
  {
    private ArrayList mListener = new ArrayList();
    private AnimationPanel.TimerProperties mProp = null;
    
    public TimerListener(AnimationPanel paramAnimationPanel, AnimationPanel.TimerProperties paramTimerProperties)
    {
      addListener(paramAnimationPanel);
      mProp = paramTimerProperties;
    }
    
    public TimerListener() {}
    
    public synchronized void actionPerformed(ActionEvent paramActionEvent)
    {
      if (mProp == null)
      {
        if (mImageArray == null)
        {
          if (mTimer != null) {
            mTimer.stop();
          }
          return;
        }
        AnimationPanel.this.update();
        mCurrentIndex += 1;
        if (mCurrentIndex >= mImageArray.size()) {
          if (doLoop() == true) {
            mCurrentIndex = 0;
          } else {
            mTimer.stop();
          }
        }
        return;
      }
      verifyListener();
      int i = 0;
      for (int j = 0; j < mListener.size(); j++)
      {
        AnimationPanel localAnimationPanel1 = (AnimationPanel)mListener.get(j);
        if (i < localAnimationPanel1.getAnimationCount()) {
          i = localAnimationPanel1.getAnimationCount();
        }
      }
      int k = mProp.mCurrentIndex;
      for (int m = 0; m < mListener.size(); m++)
      {
        AnimationPanel localAnimationPanel2 = (AnimationPanel)mListener.get(m);
        if (m == 0)
        {
          k++;
          if (k >= i) {
            if (doLoop() == true) {
              k = 0;
            } else {
              mTimer.stop();
            }
          }
          mProp.mCurrentIndex = k;
        }
        if (localAnimationPanel2 != null) {
          localAnimationPanel2.update4Timer(k);
        }
      }
    }
    
    public synchronized void addListener(AnimationPanel paramAnimationPanel)
    {
      for (int i = 0; i < mListener.size(); i++)
      {
        AnimationPanel localAnimationPanel = (AnimationPanel)mListener.get(i);
        if (localAnimationPanel == paramAnimationPanel) {
          return;
        }
      }
      mListener.add(paramAnimationPanel);
    }
    
    public synchronized void deleteListener(AnimationPanel paramAnimationPanel)
    {
      for (int i = 0; i < mListener.size(); i++)
      {
        AnimationPanel localAnimationPanel = (AnimationPanel)mListener.get(i);
        if (localAnimationPanel == paramAnimationPanel) {
          mListener.remove(i);
        }
      }
    }
    
    public int getListenerNum()
    {
      verifyListener();
      return mListener.size();
    }
    
    private void verifyListener()
    {
      for (int i = mListener.size() - 1; i >= 0; i--)
      {
        AnimationPanel localAnimationPanel = (AnimationPanel)mListener.get(i);
        if (localAnimationPanel.getAnimationCount() < 2) {
          mListener.remove(i);
        }
      }
    }
  }
  
  class TimerProperties
  {
    public int mCurrentIndex = 0;
    public AnimationPanel.TimerListener mListener = null;
    public Timer mTimer = null;
    
    TimerProperties() {}
  }
}
