/********* pdfViewer.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "PDFNavigationController.h"
#import "PDFViewController.h"
@interface pdfViewer : CDVPlugin {
  // Member variables go here.
}

- (void)open:(CDVInvokedUrlCommand*)command;
@end

@implementation pdfViewer

- (void)open:(CDVInvokedUrlCommand*)command
{

    PDFViewController* pdfCongroller = [[PDFViewController alloc] initWithOptions:command.arguments[0]];
    PDFNavigationController *nav = [[PDFNavigationController alloc]initWithRootViewController:pdfCongroller];
    [self.viewController presentViewController:nav animated:YES completion:^{
        //需要用模态化的方式进行展示
    }];
}

@end
