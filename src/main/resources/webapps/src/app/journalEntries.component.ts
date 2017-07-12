import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';

import {MainService} from "./services/main.service";
import {JournalEntry} from "./domain/journalEntry";
import {Observable} from "rxjs";


@Component({
  selector: 'journalEntries',
  // templateUrl: 'journalEntries.html'
  templateUrl: 'entry.html'
})

export class JournalEntriesComponent implements OnInit {
  journalId: number;
  dateToSearch: Date;
  journalEntries: JournalEntry[];
  selectedJournalEntry: JournalEntry;

  constructor(private entryService: MainService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    //this is how to get variable from request
    this.route.params.subscribe(params => {
      this.journalId = +params['journalId'];
    });

    this.dateToSearch.setDate(new Date().getDate() - 5);

    this.loadEntriesForJournalAndDate(this.journalId, this.dateToSearch)
  }

  private loadEntriesForJournalAndDate(journalId: number, dateToSearch: Date) {
    let operation: Observable<JournalEntry[]> =
      this.entryService.getJournalEntriesCreatedAfterDate(dateToSearch, journalId);
    operation.subscribe(journalEntries => {
        this.journalEntries = journalEntries;
      },
      error => {
        console.log("Error: while fetching JournalEntries for Date " + dateToSearch + " and JournalId: " + journalId, error);
      });
  }

  goBack(): void {
    this.location.back();
  }

  selectJournalEntry(journalEntry: JournalEntry): void {
    this.selectedJournalEntry = journalEntry;
  }
}
