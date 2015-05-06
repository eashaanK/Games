package mineMapperWindow;

public class Grid {

	private Cell[][] cells;
	
	public Grid(){
		cells = new Cell[(int)(Window.WIDTH/Cell.CELLSIZE)][(int)(Window.HEIGHT/Cell.CELLSIZE)];
		for(int x = 0; x < cells.length; x++){
			for(int y = 0; y < cells[x].length; y++){
				cells[x][y] = new Cell(x, y);
			}
		}
	}
	
	public void draw(Robot r, float delta){
		for(int x = 0; x < cells.length; x++){
			for(int y = 0; y < cells[x].length; y++){
				if(cells[x][y].IsIntersectingRobot(r)){
					cells[x][y].activate();
					cells[x][y].updateColor(delta);
				}
				cells[x][y].render();
			}
		}
	}
	
}
