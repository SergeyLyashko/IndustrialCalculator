/*
 * Copyright 2019 Sergei Lyashko. Contacts: <9lLLLepuLLa@gmail.com>.
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
package calcmassview;

import calcmassview.base.IKeyActionObserver;
import calcmassview.CalculatorView;

/**
 * Интерфейс наблюдаемого объекта
 * @author Sergei Lyashko
 */
public interface IKeyActionSubject {
    
    /**
     * Регистрация наблюдателей
     * @param viewObserver экземпляр интерфейса Наблюдатель
     */
    public void registerObserver(CalculatorView viewObserver);
    
    /**
     * оповещение наблюдателей об изменении состояния
     */
    public void notifyObservers();
    
    
    public void registerObserver(IKeyActionObserver keyActionObserver);
}
