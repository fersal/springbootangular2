import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import {JournalEntry} from './domain/journalEntry';
import {MainService} from "./services/main.service";
import {isNullOrUndefined} from "util";
import {User} from "./domain/user";

@Component({
  selector: 'entry',
  templateUrl: './entry.html'
})

export class EntryComponent implements OnInit {
  private entry: JournalEntry;
  private journalId: number;

  currentUser: User;


  constructor(private entryService: MainService,
              private route: ActivatedRoute,
              private location: Location,) {

    this.entry = new JournalEntry();
  }

  onSave(): void {
    console.log("Will save entry in JournalId: " + this.journalId);
    //Todo: entry needs to have a Journal then the journal have its id set
    this.entry.journalId = this.journalId;
    this.entryService.saveEntry(this.entry)
      .then(savedJournal => {
        if (!isNullOrUndefined(savedJournal.journalEntryId)) {
          alert("Successfully updated your Journal");
        } else {
          alert("There was a problem saving Journal");
        }
      });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.journalId = +params['journalId'];
    });
    //ToDo: now fetch latest entry for journal and populate view and set this.journal
  }

  goBack(): void {
    this.location.back();
  }
}
