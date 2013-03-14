package Gui;

/**
 * 
 * @author philip
 * 
 *         Button Area at the bottom with start stop of simulation. Label/input
 *         data area on the right for changing enviroment variables and
 *         outputting data.
 * 
 */
public class ArrangeGui {

	GuiMain guiMain;

	public ArrangeGui(GuiMain guiMain) {

		this.guiMain = guiMain;
		createMapArea();
		createButtonArea();

	}

	private void createMapArea() {
		guiMain.add(guiMain.mapDisplay);

	}

	private void createButtonArea() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings({ "unused" })
	private ArrangeGui() {

	}

}
