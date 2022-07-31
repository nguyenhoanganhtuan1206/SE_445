import {Component, OnChanges, OnInit} from '@angular/core';
import {MigrationService} from "../service/migration.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FileUpload} from "../models/FileUpload";

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit, OnChanges {

  migrationForm: FormGroup;

  /* To store file chosen */
  selectedFiles: any;
  urls = [];
  fileUpload: FileUpload;

  percentage: any;
  transformed = false;

  /* Show file failed */
  fileFailed = [];
  fileSuccess = [];

  /* To store file uploaded */
  storeFileTemp: any[] = [];

  /* To store all record */
  // storeRecordsSuccess = new Map<any , any>();
  storeRecordsSuccess: any[] = [];
  storeRecordTemp = 0;
  // storeRecordsSuccess = 0;

  constructor(private migrationService: MigrationService, private fb: FormBuilder) {
  }

  ngOnChanges(): void {
    console.log('hi')
  }

  ngOnInit(): void {
    this.migrationForm = this.fb.group({
      file: ['', Validators.required]
    });
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    let files = event.target.files;
    if (files) {
      for (let file of files) {
        this.fileUpload = new FileUpload(file);

        this.urls.push(this.fileUpload);
      }
    }
  }

  transform() {
    for (let i = 0; i < this.urls.length; i++) {
      /* Store file uploaded */
      this.storeFileTemp.push(this.urls[i]);

      this.migrationService.tsvToMaria(this.urls[i].file.name).subscribe((records: any) => {
        this.transformed = true;
        
        this.fileSuccess = this.storeFileTemp;
      }, error => {
      }, () => {
        /* Push file and show progress */
        for (let i = 0; i < this.urls.length; i++) {
          this.migrationService.pushFileToStorage(this.urls[i]).subscribe(percentage => {
            this.percentage = percentage;

            /* Reset after migrate */
            if (percentage == 100) {
              this.migrationForm.reset();
              this.urls = [];
            }
          });
        }
      });
    }
  }
}
