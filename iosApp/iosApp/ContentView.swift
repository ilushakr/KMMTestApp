import SwiftUI
import sharedbase
import sharedmain

class MainViewModel: ObservableObject{
    
    private let usersController = UsersControllerCompanion.shared.doInitController()
    
    @Published var users: [User]? = [User]()
    
    init() {
        usersController.onChange { state in
            if (state is UsersStore.UsersState){
                self.users = (state as! UsersStore.UsersState).data
            }
        }
    }
    
    func getUsersApi(){
        usersController.getUsersApi()
    }
    
    func getUsersDB(){
        usersController.getUsersDb()
    }
    
}

struct ContentView: View {
    
	let greet = Greeting().greeting()

    @StateObject var t = MainViewModel()

	var body: some View {
        VStack{
            Button("get api"){
                t.getUsersApi()
            }
            Button("get db"){
                t.getUsersDB()
            }
            Text(String(t.users?.map { $0.getString() }.joined(separator: "\n") ?? "emptr"))
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
