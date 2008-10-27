package com.explodingpixels.macwidgets;

import com.explodingpixels.painter.Painter;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class MacPainterFactory {

    ///////////////////////////////////////////////////////////////////////////
    // iApp Header painters.
    ///////////////////////////////////////////////////////////////////////////

    public static Painter<Component> createIAppUnpressedUnselectedHeaderPainter() {
        return new Painter<Component>() {

            private Color TOP_GRADIENT_COLOR = new Color(0xdbdbdb);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0xbbbbbb);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }

    public static Painter<Component> createIAppPressedUnselectedHeaderPainter() {
        return new Painter<Component>() {

            private Color TOP_GRADIENT_COLOR = new Color(0xc4c4c4);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0x959595);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }

    public static Painter<Component> createIAppUnpressedSelectedHeaderPainter() {
        return new Painter<Component>() {

            private Color TOP_GRADIENT_COLOR = new Color(0xc2cfdd);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0x7d93b2);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_GRADIENT_COLOR, MacColorUtils.LEOPARD_BORDER_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }


    public static Painter<Component> createIAppPressedSelectedHeaderPainter() {
        return new Painter<Component>() {

            private Color TOP_GRADIENT_COLOR = new Color(0xa6b7cb);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0x536b90);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_GRADIENT_COLOR, MacColorUtils.LEOPARD_BORDER_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // Selection painters.
    ///////////////////////////////////////////////////////////////////////////

    public static Painter<Component> createSourceListSelectionPainter_componentFocused() {
        return new Painter<Component>() {

            private Color TOP_LINE_COLOR = new Color(0x4580c8);
            private Color TOP_GRADIENT_COLOR = new Color(0x5d94d6);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0x1956ad);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_LINE_COLOR, BOTTOM_GRADIENT_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }

    public static Painter<Component> createSourceListSelectionPainter_windowFocused() {
        return new Painter<Component>() {

            private Color TOP_LINE_COLOR = new Color(0x91a0c0);
            private Color TOP_GRADIENT_COLOR = new Color(0xa1b0cf);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0x7185ab);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_LINE_COLOR, BOTTOM_GRADIENT_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }

    public static Painter<Component> createSourceListSelectionPainter_windowUnfocused() {
        return new Painter<Component>() {

            private Color TOP_LINE_COLOR = new Color(0x979797);
            private Color TOP_GRADIENT_COLOR = new Color(0xb4b4b4);
            private Color BOTTOM_GRADIENT_COLOR = new Color(0x8a8a8a);

            public void paint(Graphics2D graphics2D, Component component,
                              int width, int height) {
                paintLeopardGradientSelection(
                        TOP_LINE_COLOR, BOTTOM_GRADIENT_COLOR,
                        TOP_GRADIENT_COLOR, BOTTOM_GRADIENT_COLOR,
                        graphics2D, width, height);
            }
        };
    }

    /**
     * Paints a graident with a single pixel border at the top of the component
     * and a single pixel border at the bottom.
     *
     * @param topLineColor        the color of the top line border.
     * @param bottomLineColor     the color of the bottom line border.
     * @param topGradientColor    the top color to use in creating the gradient
     *                            paint.
     * @param bottomGradientColor the bottom color to use in creating the
     *                            gradient paint.
     * @param graphics2D          the {@link Graphics2D} to render into. This graphics
     *                            context need not be restore state upon completion.
     * @param width               the width of the component to render into.
     * @param height              the height of the component to render into.
     */
    private static void paintLeopardGradientSelection(
            Color topLineColor,
            Color bottomLineColor,
            Color topGradientColor,
            Color bottomGradientColor,
            Graphics2D graphics2D,
            int width, int height) {
        // create the paint - start the gradient one pixel from the top
        // of the component and finish the gradient one pixel from the
        // bottom.
        GradientPaint paint = new GradientPaint(
                0, 1, topGradientColor,
                0, height - 2, bottomGradientColor);
        // install the paint and fill a rectangle with it.
        graphics2D.setPaint(paint);
        graphics2D.fillRect(0, 0, width, height);
        // set the graphics color and draw a line across the top of the
        // component.
        graphics2D.setColor(topLineColor);
        graphics2D.drawLine(0, 0, width, 0);
        // set the graphics color and draw a line across the bottom of the
        // component.
        graphics2D.setColor(bottomLineColor);
        graphics2D.drawLine(0, height - 1, width, height - 1);
    }

}