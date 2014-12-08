package sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SoldierSprite extends Sprite {
  private static BufferedImage image;
  private static final int leftBorder = 3, vertBorder = 4;
  private static final int width = 40, height = 50;
  private static final int MAX_FRAMES = 3;
  private State lastState;

  public SoldierSprite() {
    if (image == null)
      try {
        image = ImageIO.read(new File("soldier.png"));
      } catch (Exception e) {
      }
    ;
    lastState = State.MOVING_DOWN;
  }

  public void setState(State state) {
    lastState = getState();
    super.setState(state);
  }

  @Override
  public Image getImage() {
    if (image == null)
      return null;

    int row = 0, col = frame, offset = 3;

    switch (getState()) {
    case IDLE:
      col = 0;
      switch (lastState) {
      case MOVING_UP:
        row = 1;
        break;
      case MOVING_DOWN:
        row = 0;
        break;
      case MOVING_LEFT:
        row = 2;
        break;
      case MOVING_RIGHT:
        row = 3;
        break;
      default:
        break;
      }
     break;
    case MOVING_UP:
      row = 1;
      break;
    case MOVING_DOWN:
      row = 0;
      break;
    case MOVING_LEFT:
      row = 2;
      break;
    case MOVING_RIGHT:
      row = 3;
      break;
    }

    if (row == 3 && (col == 4 || col == 1))
      offset = 4;

    frame = (frame + 1) % MAX_FRAMES;
    return image.getSubimage(leftBorder + col * width, row * (height + vertBorder),
        width - offset, height);
  }

  @Override
  public int getHeight() {
    // TODO Auto-generated method stub
    return height;
  }

  @Override
  public int getWidth() {
    // TODO Auto-generated method stub
    return width;
  }
}
