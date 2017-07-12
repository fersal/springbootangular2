import {OnInit, Component} from "@angular/core";
import {Journal} from "./domain/journal";
import {MainService} from "./services/main.service";
import {isNullOrUndefined} from "util";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'journals',
  templateUrl: 'journals.html'
})

export class JournalsComponent implements OnInit {
  journalSize: number;
  selectedJournal: Journal;
  journals: Journal[];

  constructor(private entryService: MainService,
              private router: Router) {}

  ngOnInit(): void {

    this.loadJournals(1);

    if (!isNullOrUndefined(this.journals)) {
      this.journalSize = this.journals.length;
    }
  }

  loadJournals(userId: number): void {
    let operation:Observable<Journal[]> = this.entryService.getJournalsForUserId(userId);
    operation.subscribe(journals => {
        this.journals = journals;
        this.journalSize = this.journals.length;
      },
      error => {
        console.log("There was an error while waiting on Journals subscription: " + error)
      });
  }

  onSelectJournal(journal: Journal): void {
    this.selectedJournal = journal;
  }

  addEntry(): void {
    let linkToEntryForJournal = ['/entry', this.selectedJournal.journalId];
    this.router.navigate(linkToEntryForJournal);
  }

  viewHistory(): void {
    let linkToJournalEntries = ['/journalEntries', this.selectedJournal.journalId];
    this.router.navigate(linkToJournalEntries);
  }
}
