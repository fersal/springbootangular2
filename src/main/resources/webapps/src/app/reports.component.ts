  import 'rxjs/add/operator/switchMap'
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';

import {JournalEntry} from './domain/journalEntry';
import {Journal} from './domain/journal';
import {MainService} from './services/main.service';
import {isNullOrUndefined} from "util";
import {Observable} from "rxjs";

@Component({
  selector: 'reports',
  templateUrl: 'reports.html'
})

export class ReportsComponent implements OnInit {

  entryList: JournalEntry[];
  selectedEntry: JournalEntry;


  constructor(private entryService: MainService,
              private route: ActivatedRoute) {
  }



  onSelectEntry(selectedJournal: Journal): void {
  }

  ngOnInit(): void {
    // this.route.params
    //   .switchMap((params: Params) =>
    //     this.entryService.getJournalsForUserId(1))
    //   .then(journals => this.journals = journals);


  }


}
