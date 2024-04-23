/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.menapln.Reporting;

import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;

/**
 *
 * @author coulibaly
 */
 class RoundedCellRenderer extends CellRenderer {
    protected float[] cmykColor;
    protected boolean isColoredBackground;
 
    public RoundedCellRenderer(Cell modelElement, float[] cmykColor, boolean isColoredBackground) {
        super(modelElement);
        modelElement.setBorder(Border.NO_BORDER);
        this.cmykColor = cmykColor;
        this.isColoredBackground = isColoredBackground;
    }
 
    @Override
    public void drawBackground(DrawContext drawContext) {
        Rectangle rect = getOccupiedAreaBBox();
        PdfCanvas canvas = drawContext.getCanvas();
        canvas
                .saveState()
                .roundRectangle(rect.getLeft() + 2.5f, rect.getBottom() + 2.5f, rect.getWidth() - 5, rect.getHeight() - 5, 6)
                .setStrokeColorCmyk(cmykColor[0], cmykColor[1], cmykColor[2], cmykColor[3])
                .setLineWidth(1.5f);
        if (isColoredBackground) {
            canvas.setFillColor(new DeviceCmyk(cmykColor[0], cmykColor[1], cmykColor[2], cmykColor[3])).fillStroke();
        } else {
            canvas.stroke();
        }
        canvas.restoreState();
 
    }
}
