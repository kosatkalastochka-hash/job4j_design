package ru.job4j.assertj;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void wenWhatsThisSphere() {
        Box box = new Box(0, 4);
        String result = box.whatsThis();
        assertThat(result).isNotEmpty()
                .doesNotContain("figure")
                .isEqualTo("Sphere");
    }

    @Test
    void wenWhatsThisTetrahedron() {
        Box box = new Box(4, 7);
        String result = box.whatsThis();
        assertThat(result).isNotEmpty()
                .doesNotContain("figure")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void wenWhatsThisCube() {
        Box box = new Box(8, 7);
        String result = box.whatsThis();
        assertThat(result).isNotEmpty()
                .doesNotContain("figure")
                .isEqualTo("Cube");
    }

    @Test
    void wenWhatsThisUNKNOWN() {
        Box box = new Box(10, 7);
        String result = box.whatsThis();
        assertThat(result).isNotEmpty()
                .endsWith("object")
                .isEqualTo("Unknown object");
    }

    @Test
    void wenInitEdgeNegative() {
        Box box = new Box(0, -7);
        String result = box.whatsThis();
        assertThat(result).isNotEmpty()
                .startsWithIgnoringCase("unknown")
                .isEqualTo("Unknown object");
    }

     @Test
    void getNumberOfVerticesWenExist() {
        Box box = new Box(8, 3);
        int result = box.getNumberOfVertices();
        assertThat(result).isPositive()
                .isGreaterThan(-1)
                .isLessThan(9)
                .isIn(0, 4, 8);
    }

    @Test
    void getNumberOfVerticesWenNotExist() {
        Box box = new Box(10, 3);
        int result = box.getNumberOfVertices();
        assertThat(result).isNegative()
                .isOdd()
                .isIn(-1, -2);
    }

    @Test
    void isExistWenExist() {
        Box box = new Box(0, 4);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isExistWenNotExist() {
        Box box = new Box(-1, 4);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void  getAreaWenSphere() {
        double a = 4.0;
        Box box = new Box(0, (int) a);
        double result = box.getArea();
        assertThat(result).isPositive()
                .isGreaterThan(4 * a * a)
                .isLessThan(16 * a * a)
                .isEqualTo(4 * Math.PI * (a * a));
    }

    @Test
    void  getAreaWenTetrahedron() {
        double a = 4.0;
        Box box = new Box(4, (int) 4);
        double result = box.getArea();
        assertThat(result).isPositive()
                .isGreaterThan(a * a)
                .isLessThan(3 * a * a)
                .isEqualTo(Math.sqrt(3) * (a * a));
    }

    @Test
    void  getAreaWenCube() {
        double a = 4.0;
        Box box = new Box(8, (int) 4);
        double result = box.getArea();
        assertThat(result).isPositive()
                .isEqualTo(6 * (a * a));
    }

    @Test
    void  getAreaWenNotExist() {
        Box box = new Box(12, 4);
        double result = box.getArea();
        assertThat(result) .isGreaterThan(-20.0)
                .isLessThan(1.0)
                .isEqualTo(0.0);
    }
}