/*
 * Copyright 2020 Korvin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package calcmassmodel;

/**
 *
 * @author Korvin
 */
class Detail {

    private final double length;
    private final double width;
    private final double detailValue;

    Detail(double length, double width, double detailValue) {
        this.length = length;
        this.width = width;
        this.detailValue = detailValue;
    }

    double getDataBaseValue() {
        return detailValue;
    }

    double getLength() {
        return length;
    }

    double getWidth() {
        return width;
    }
}
