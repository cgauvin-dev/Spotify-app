import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SearchComponent} from './pages/search/search.component';


const routes: Routes = [
    {path: '', redirectTo: '/search', pathMatch: 'full'},
    {path: 'search', component: SearchComponent},
];

@NgModule({
    declarations: [],
    imports: [
        RouterModule.forRoot(routes)//{onSameUrlNavigation: 'reload'}
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
