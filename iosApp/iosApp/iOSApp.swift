import SwiftUI
import sharedmain

@main
struct iOSApp: App {
    
    init() {
        KoinHelper().doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
    
}
