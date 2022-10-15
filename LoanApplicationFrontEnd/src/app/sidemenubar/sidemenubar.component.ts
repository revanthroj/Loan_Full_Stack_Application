import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidemenubar',
  templateUrl: './sidemenubar.component.html',
  styleUrls: ['./sidemenubar.component.css'],
})
export class SidemenubarComponent implements OnInit {
  title = 'Fx-Trading';
  rate = 0;
  i = 0;
  constructor() {}

  ngOnInit(): void {
    const body: any = document.querySelector('body'),
      sidebar: any = body.querySelector('nav'),
      toggle: any = body.querySelector('.toggle'),
      modeSwitch: any = body.querySelector('.toggle-switch'),
      modeText: any = body.querySelector('.mode-text');

    toggle.addEventListener('click', () => {
      sidebar.classList.toggle('close');
      this.i = this.i + 1;
      console.log(this.i);
      let popup: any = document.getElementById('RatePopup');
      popup.classList.remove('open-popup');
    });

    modeSwitch.addEventListener('click', () => {
      body.classList.toggle('dark');

      if (body.classList.contains('dark')) {
        modeText.innerText = 'Light mode';
      } else {
        modeText.innerText = 'Dark mode';
      }
    });
  }
}
