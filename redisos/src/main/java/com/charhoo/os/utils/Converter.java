
package com.charhoo.os.utils;

import java.util.Date;

public class Converter {

    Converter me;

    double casm_rr = 0.0D;

    double casm_t1 = 0.0D;

    double casm_t2 = 0.0D;

    double casm_x1 = 0.0D;

    double casm_y1 = 0.0D;

    double casm_x2 = 0.0D;

    double casm_y2 = 0.0D;

    double casm_f = 0.0D;

    public Converter() {
        this.me = this;
        this.casm_rr = 0.0D;
        this.casm_t1 = 0.0D;
        this.casm_t2 = 0.0D;
        this.casm_x1 = 0.0D;
        this.casm_y1 = 0.0D;
        this.casm_x2 = 0.0D;
        this.casm_y2 = 0.0D;
        this.casm_f = 0.0D;
    }

    protected double yj_sin2(double x) {
        double ff = 0.0D;
        if (x < 0.0D) {
            x = -x;
            ff = 1.0D;
        }

        int cc = (int) (x / 6.28318530717959D);

        double tt = x - cc * 6.28318530717959D;
        if (tt > 3.141592653589793D) {
            tt -= 3.141592653589793D;
            if (ff == 1.0D) {
                ff = 0.0D;
            } else if (ff == 0.0D) {
                ff = 1.0D;
            }
        }
        x = tt;
        double ss = x;
        double s2 = x;
        tt *= tt;
        s2 *= tt;
        ss -= s2 * 0.166666666666667D;
        s2 *= tt;
        ss += s2 * 0.00833333333333333D;
        s2 *= tt;
        ss -= s2 * 0.000198412698412698D;
        s2 *= tt;
        ss += s2 * 2.75573192239859E-006D;
        s2 *= tt;
        ss -= s2 * 2.50521083854417E-008D;
        if (ff == 1.0D) {
            ss = -ss;
        }
        return ss;
    }

    protected double Transform_yj5(double x, double y) {
        double tt = 300.0D + 1.0D * x + 2.0D * y + 0.1D * x * x + 0.1D * x * y + 0.1D * Math.sqrt(Math.sqrt(x * x));
        tt += (20.0D * this.me.yj_sin2(18.849555921538762D * x) + 20.0D * this.me.yj_sin2(6.283185307179588D * x)) * 0.6667D;
        tt += (20.0D * this.me.yj_sin2(3.141592653589794D * x) + 40.0D * this.me.yj_sin2(1.047197551196598D * x)) * 0.6667D;
        tt += (150.0D * this.me.yj_sin2(0.2617993877991495D * x) + 300.0D * this.me.yj_sin2(0.1047197551196598D * x)) * 0.6667D;
        return tt;
    }

    protected double Transform_yjy5(double x, double y) {
        double tt = -100.0D + 2.0D * x + 3.0D * y + 0.2D * y * y + 0.1D * x * y + 0.2D * Math.sqrt(Math.sqrt(x * x));
        tt += (20.0D * this.me.yj_sin2(18.849555921538762D * x) + 20.0D * this.me.yj_sin2(6.283185307179588D * x)) * 0.6667D;
        tt += (20.0D * this.me.yj_sin2(3.141592653589794D * y) + 40.0D * this.me.yj_sin2(1.047197551196598D * y)) * 0.6667D;
        tt += (160.0D * this.me.yj_sin2(0.2617993877991495D * y) + 320.0D * this.me.yj_sin2(0.1047197551196598D * y)) * 0.6667D;
        return tt;
    }

    protected double Transform_jy5(double x, double xx) {
        double a = 6378245.0D;
        double e = 0.00669342D;
        double n = Math.sqrt(1.0D - e * this.me.yj_sin2(x * 0.0174532925199433D)
                * this.me.yj_sin2(x * 0.0174532925199433D));
        n = xx * 180.0D / (a / n * Math.cos(x * 0.0174532925199433D) * 3.1415926D);
        return n;
    }

    protected double Transform_jyj5(double x, double yy) {
        double a = 6378245.0D;
        double e = 0.00669342D;
        double mm = 1.0D - e * this.me.yj_sin2(x * 0.0174532925199433D) * this.me.yj_sin2(x * 0.0174532925199433D);
        double m = a * (1.0D - e) / (mm * Math.sqrt(mm));
        return yy * 180.0D / (m * 3.1415926D);
    }

    protected int r_yj() {
        int casm_a = 314159269;
        int casm_c = 453806245;
        return 0;
    }

    protected double random_yj() {
        double casm_a = 314159269.0D;
        double casm_c = 453806245.0D;
        this.me.casm_rr = (casm_a * this.me.casm_rr + casm_c);
        double t = (int) (this.me.casm_rr / 2.0D);
        this.me.casm_rr -= t * 2.0D;
        this.me.casm_rr /= 2.0D;
        return this.me.casm_rr;
    }

    protected void IniCasm(double w_time, double w_lng, double w_lat) {
        this.me.casm_t1 = w_time;
        this.me.casm_t2 = w_time;
        double tt = (int) (w_time / 0.357D);
        this.me.casm_rr = (w_time - tt * 0.357D);
        if (w_time == 0.0D)
            this.me.casm_rr = 0.3D;
        this.me.casm_x1 = w_lng;
        this.me.casm_y1 = w_lat;
        this.me.casm_x2 = w_lng;
        this.me.casm_y2 = w_lat;
        this.me.casm_f = 3.0D;
    }

    protected Point wgtochina_lb(int wg_flag, int wg_lng, int wg_lat, int wg_heit, int wg_week, int wg_time) {
        Point point = null;
        if (wg_heit > 5000) {
            return point;
        }
        double x_l = wg_lng;
        x_l /= 3686400.0D;
        double y_l = wg_lat;
        y_l /= 3686400.0D;
        if (x_l < 72.004000000000005D) {
            return point;
        }
        if (x_l > 137.8347D) {
            return point;
        }
        if (y_l < 0.8293D) {
            return point;
        }
        if (y_l > 55.827100000000002D) {
            return point;
        }
        if (wg_flag == 0) {
            this.me.IniCasm(wg_time, wg_lng, wg_lat);
            point = new Point();
            point.setLatitude(wg_lng);
            point.setLongitude(wg_lat);
            return point;
        }
        this.me.casm_t2 = wg_time;
        double t1_t2 = (this.me.casm_t2 - this.me.casm_t1) / 1000.0D;
        if (t1_t2 <= 0.0D) {
            this.me.casm_t1 = this.me.casm_t2;
            this.me.casm_f += 1.0D;
            this.me.casm_x1 = this.me.casm_x2;
            this.me.casm_f += 1.0D;
            this.me.casm_y1 = this.me.casm_y2;
            this.me.casm_f += 1.0D;
        } else if (t1_t2 > 120.0D) {
            if (this.me.casm_f == 3.0D) {
                this.me.casm_f = 0.0D;
                this.me.casm_x2 = wg_lng;
                this.me.casm_y2 = wg_lat;
                double x1_x2 = this.me.casm_x2 - this.me.casm_x1;
                double y1_y2 = this.me.casm_y2 - this.me.casm_y1;
                double casm_v = Math.sqrt(x1_x2 * x1_x2 + y1_y2 * y1_y2) / t1_t2;
                if (casm_v > 3185.0D) {
                    return point;
                }
            }
            this.me.casm_t1 = this.me.casm_t2;
            this.me.casm_f += 1.0D;
            this.me.casm_x1 = this.me.casm_x2;
            this.me.casm_f += 1.0D;
            this.me.casm_y1 = this.me.casm_y2;
            this.me.casm_f += 1.0D;
        }

        double x_add = this.me.Transform_yj5(x_l - 105.0D, y_l - 35.0D);
        double y_add = this.me.Transform_yjy5(x_l - 105.0D, y_l - 35.0D);
        double h_add = wg_heit;
        x_add = x_add + h_add * 0.001D + this.me.yj_sin2(wg_time * 0.0174532925199433D) + this.me.random_yj();
        y_add = y_add + h_add * 0.001D + this.me.yj_sin2(wg_time * 0.0174532925199433D) + this.me.random_yj();
        point = new Point();
        point.setX((x_l + this.me.Transform_jy5(y_l, x_add)) * 3686400.0D);
        point.setY((y_l + this.me.Transform_jyj5(y_l, y_add)) * 3686400.0D);
        return point;
    }

    protected boolean isValid(long validdays) {
        long h = 3600L;
        Date currentTime = new Date();

        return currentTime.getTime() / 1000L - 1253525356L >= validdays * 24L * h;
    }

    public Point getEncryPoint(double x, double y) {
        Point point = new Point();
        if (!isValid(10L)) {
            return point;
        }

        double x1 = x * 3686400.0D;
        double y1 = y * 3686400.0D;
        double gpsWeek = 0.0D;
        double gpsWeekTime = 0.0D;
        double gpsHeight = 0.0D;

        point = this.me.wgtochina_lb(1, (int) x1, (int) y1, (int) gpsHeight, (int) gpsWeek, (int) gpsWeekTime);
        double tempx = point.getX();
        double tempy = point.getY();
        tempx /= 3686400.0D;
        tempy /= 3686400.0D;
        point = new Point();
        point.setX(tempx);
        point.setY(tempy);
        return point;
    }

    protected String getEncryCoord(String coord, boolean flag) {
        if (flag) {
            double x = Double.parseDouble(coord.split(",")[0]);
            double y = Double.parseDouble(coord.split(",")[1]);
            Point point = new Point();

            double x1 = x * 3686400.0D;
            double y1 = y * 3686400.0D;
            int gpsWeek = 0;
            int gpsWeekTime = 0;
            int gpsHeight = 0;
            point = this.me.wgtochina_lb(1, (int) x1, (int) y1, gpsHeight, gpsWeek, gpsWeekTime);
            double tempx = point.getX();
            double tempy = point.getY();
            tempx /= 3686400.0D;
            tempy /= 3686400.0D;
            return tempx + "," + tempy;
        }

        return "";
    }

    protected void getEncryCoords(String coords, boolean flag) {
    }

    public Point getDecryPoint(double x, double y, int nIteration) {
        Point p1 = new Point();

        p1.x = x;
        p1.y = y;

        Point p2 = getEncryPoint(p1.x, p1.y);
        double dx = p2.x - x;
        double dy = p2.y - y;

        p1.longitude = (x - dx);
        p1.latitude = (y - dy);

        p2 = getEncryPoint(p1.longitude, p1.latitude);
        p1.deviation = (Math.abs(p2.x - p1.x) + Math.abs(p2.y - p2.x));

        for (int i = 1; i <= nIteration; i++) {
            p2 = getEncryPoint(p1.longitude, p1.latitude);
            dx = p2.x - p1.x;
            dy = p2.y - p1.y;

            p1.longitude -= dx;
            p1.latitude -= dy;
        }

        return p1;
    }

    public Point getDecryPointSuper(double x, double y, int nIteration) {
        Point p1 = new Point();
        double deviation = 0.0D;

        p1.x = x;
        p1.y = y;

        Point p2 = getEncryPoint(p1.x, p1.y);
        Point p3 = new Point();

        double dx = p2.x - x;
        double dy = p2.y - y;
        p1.longitude = (x - dx);
        p1.latitude = (y - dy);

        for (int i = 0; i <= nIteration; i++) {
            if (i > 0) {
                p2 = getEncryPoint(p1.longitude, p1.latitude);
                dx = p2.x - p1.x;
                dy = p2.y - p1.y;

                p1.longitude -= dx;
                p1.latitude -= dy;

                p3 = getEncryPoint(p2.longitude, p2.latitude);
                dx = p3.x - p1.x;
                dy = p3.y - p1.y;

                deviation = Math.abs(dx) + Math.abs(dy) + 1.0D;
                if (deviation >= p1.deviation)
                    break;
                p1.longitude = p2.longitude;
                p1.latitude = p2.latitude;
                p1.deviation = deviation;
            } else {
                p2 = getEncryPoint(p1.longitude, p1.latitude);
                dx = p2.x - p1.x;
                dy = p2.y - p1.y;
                p1.deviation = (Math.abs(dx) + Math.abs(dy) + 1.0D);
            }

        }

        return p1;
    }
}